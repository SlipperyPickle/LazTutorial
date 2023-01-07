import branch.CharacterPart
import org.powbot.api.Condition
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.OptionType
import org.powbot.api.script.ScriptCategory
import org.powbot.api.script.ScriptConfiguration
import org.powbot.api.script.ScriptManifest
import org.powbot.api.script.paint.Paint
import org.powbot.api.script.paint.PaintBuilder
import org.powbot.api.script.tree.TreeComponent
import org.powbot.api.script.tree.TreeScript
import org.powbot.mobile.SettingsManager
import org.powbot.mobile.ToggleId
import java.util.logging.Logger
import kotlin.random.Random

@ScriptManifest(
    name = "Laz Tutorial Island",
    description = "Does tutorial island",
    version = "1.0.0",
    category = ScriptCategory.Other,
    author = "Lazarus"
)

@ScriptConfiguration(
    name = "Random looks?",
    description = "Do you want your character to look different?",
    optionType = OptionType.BOOLEAN,
    defaultValue = "true"
)
@ScriptConfiguration(
    name = "Male?",
    description = "Do you want your character to be male?",
    optionType = OptionType.BOOLEAN,
    defaultValue = "true"
)
@ScriptConfiguration(
    name = "Fresh Start World?",
    description = "Do you want to enter fresh start worlds?",
    optionType = OptionType.BOOLEAN,
    defaultValue = "false "
)

class Script : TreeScript() {
    override val rootComponent: TreeComponent<*> by lazy {
        CharacterPart(this)
    }

    lateinit var settings: Settings
    val logger: Logger = Logger.getLogger(this.javaClass.simpleName)

    private fun getSettings() {
        val randomizeLooks = getOption<Boolean>("Random looks?")
        val male = getOption<Boolean>("Male?")
        val freshStartWorld = getOption<Boolean>("Fresh Start World?")

        settings = Settings(randomizeLooks, male, freshStartWorld)
    }

    override fun onStart() {
        getSettings()
        addPaint()
        SettingsManager.set(ToggleId.HideChatBox, false)
        SettingsManager.set(ToggleId.DaxWebWalking, true)
        Condition.sleep(Random.nextInt(300, 450))
    }

    private fun addPaint() {
        val p: Paint = PaintBuilder.newBuilder()
            .addString("Last leaf:") { lastLeaf.toString() }
            .addString("Varpbit status:") { Varpbits.varpbit(281).toString() }
//            .y(45)
//            .x(40)
            .build()
        addPaint(p)
    }
}

fun main(args: Array<String>) {
    Script().startScript()
}
