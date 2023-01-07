package leafs

import Script
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Widgets
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.script.ScriptManager

class StopScript(script: Script) : Leaf<Script>(script, "Done") {

    override fun execute() {
        Game.logout()
        ScriptManager.stop()

    }
}