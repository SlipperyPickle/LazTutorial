package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Objects
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class CookShrimps(script: Script) : Leaf<Script>(script, "CookShrimps") {

    override fun execute() {
        val fire = Objects.stream().id(26185).nearest()
        if (fire.isEmpty()) makeFire()

        val rawShrimps = Inventory.stream().name("Raw shrimps")
        if (rawShrimps.isNotEmpty()) {
            rawShrimps.first().interact("Use")
        }
        if (Inventory.selectedItem().id() == rawShrimps.first().id) {
            fire.first().interact("Use")
            Condition.wait ({ Inventory.stream().name("Raw shrimps").isEmpty() }, 400, 20)
        }

    }

    private fun makeFire() {
        val tree = Objects.stream().id(9730).nearest().first()
        if (Inventory.stream().name("Logs").isEmpty()) {
            if (tree.inViewport()) {
                tree.interact("Chop down", "Tree")
                Condition.wait ({ Inventory.stream().id(2511).isNotEmpty() }, 400, 20)

            }
        }

        if (!Inventory.stream().name("Logs").isEmpty()) {
            if (!Inventory.stream().name("Tinderbox").isEmpty()) {
                val tinderbox = Inventory.stream().name("Tinderbox").first()
                val logs = Inventory.stream().name("Logs").first()
                if (Inventory.selectedItem().id() == -1) {
                    tinderbox.interact("Use")
                    Condition.sleep(Random.nextInt(300, 450))
//                    Condition.wait({ Inventory.selectedItem().id() != -1 }, 350, 10)
                }
                if (Inventory.selectedItem().id() == tinderbox.id()) {
                    logs.interact("Use")
                    Condition.wait ({ Objects.stream().id(26185).isNotEmpty() }, 400, 20)
                }
            }
        }
    }
}