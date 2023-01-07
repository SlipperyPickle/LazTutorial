package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf

class ChangeGender(script: Script) : Leaf<Script>(script, "Change Gender") {

    override fun execute() {
        val maleWidget: Component = Widgets.widget(679).component(65)
        val femaleWidget: Component = Widgets.widget(679).component(66)
        when(script.settings.gender && maleWidget.visible()) {
            true -> if (maleWidget.actions()[0] != "") maleWidget.click()
            false -> if (femaleWidget.actions()[0] != "") femaleWidget.click()
        }

        Condition.sleep(Random.nextInt(200, 400))
    }
}