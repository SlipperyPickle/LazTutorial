package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf

class Fish(script: Script) : Leaf<Script>(script, "Fish") {

    override fun execute() {
        val fishingSpot = Npcs.stream().id(3317).nearest().first() //Objects.stream().id(3317).nearest().first()
        if (Inventory.stream().name("Raw shrimps").isEmpty()) {
            if (fishingSpot.inViewport()) {
                fishingSpot.interact("Net", "Fishing spot")
                Condition.wait {
                    Inventory.stream().name("Raw shrimps").isNotEmpty()
                }
            }
        }
//
//        val statsWidget = Widgets.widget(601).component(66)
//        if (statsWidget.visible()) {
//            statsWidget.click()
//            Condition.wait({ Game.tab() == Game.Tab.STATS }, 150, 10)
//        }


    }
}