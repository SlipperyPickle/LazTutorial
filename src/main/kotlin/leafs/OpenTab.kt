package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Equipment
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Widgets
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class OpenTab(script: Script, private val tabWidget: Game.Tab ) : Leaf<Script>(script, "Open $tabWidget") {

    override fun execute() {
        //TODO

        if (Game.tab(tabWidget)) Condition.sleep(Random.nextInt(400, 550))

        if (tabWidget == Game.Tab.EQUIPMENT) {
            if (Game.tab() == Game.Tab.EQUIPMENT) {
                val equipmentStatsWidget = Widgets.widget(387).component(1)
                if (equipmentStatsWidget.visible()) {
                    equipmentStatsWidget.click()
                    Condition.wait({ Widgets.widget(84).component(1).visible() }, 150, 10)
                    Condition.sleep(Random.nextInt(300, 450))
                }
            }
            if (Widgets.widget(84).component(1).visible()) if (Widgets.widget(84).component(3).component(11).click())
                Condition.wait { !Widgets.widget(84).component(1).visible() }
            Inventory.open()
            if (Equipment.stream().id(1205).isEmpty()) Inventory.stream().id(1205).first().click()
            Condition.sleep(Random.nextInt(300, 450))
        }
        if (tabWidget == Game.Tab.SETTINGS) {
            Widgets.widget(116).component(75).click()
            Condition.sleep(Random.nextInt(500, 600))
            val hideRoofs = Widgets.widget(134).component(18).component(77)
            hideRoofs.click()
            Condition.sleep(Random.nextInt(300, 450))
            //TODO fix
            val setZoom = Widgets.widget(134).component(18).component(30)
            setZoom.click()
            Condition.sleep(Random.nextInt(300, 450))
            val closeButton = Widgets.widget(134).component(4)
            closeButton.click()
            Condition.sleep(Random.nextInt(300, 450))
        }


        Game.closeOpenTab()
    }
}