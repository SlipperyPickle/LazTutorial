package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class SmithDagger(script: Script) : Leaf<Script>(script, "Smith Dagger") {

    override fun execute() {
        val smithWidget = Widgets.widget(312).component(9)
        if (!smithWidget.visible()) {
            val smithTile = Tile(3082, 9499, 0)
            if (Players.local().distanceTo(smithTile) > 4) {
                Movement.moveTo(smithTile)
                Condition.wait({ !Players.local().inMotion() }, 300, 10)
            }

            val anvil = Objects.stream().id(2097).nearest().first()
            if (anvil.inViewport()) {
                anvil.interact("Smith", "Anvil")
                Condition.wait {
                    Widgets.widget(312).component(9).visible()
                }
            }
        }
        Condition.sleep(Random.nextInt(400, 500))
        smithWidget.click()

        Condition.wait {
            Inventory.stream().id(1205).isNotEmpty()
        }

    }
}