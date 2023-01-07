package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class ChangeLooks(script: Script) : Leaf<Script>(script, "Change Looks") {
    private val widgets = mutableListOf(12, 16, 20, 24, 28, 32, 36, 43, 47, 51, 55, 59)

    override fun execute() {
        if (script.settings.randomLooks) {
            for (i in 0..11) {
                val randomInt = Random.nextInt(1, 5)
                for (u in 1..randomInt) {
                    Widgets.widget(679).component(widgets[i]).click()
                    Condition.sleep(Random.nextInt(300, 450))
                }
            }
        }
        Widgets.widget(679).component(68).click()
        Condition.wait { !Widgets.widget(679).component(68).visible() }
        Condition.sleep(Random.nextInt(500, 650))
    }
}