package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class MakeDough(script: Script) : Leaf<Script>(script, "Make Dough") {

    override fun execute() {
        val bucketOfWater = Inventory.stream().id(1929).first()
        val potOfFlour = Inventory.stream().id(2516).first()
        if (Inventory.selectedItem().id() == -1) {
            bucketOfWater.interact("Use")
            Condition.sleep(Random.nextInt(300, 450))
//                    Condition.wait({ Inventory.selectedItem().id() != -1 }, 350, 10)
        }
        if (Inventory.selectedItem().id() == bucketOfWater.id()) {
            potOfFlour.interact("Use")
            Condition.wait ({ Inventory.stream().id(1929).isEmpty() }, 400, 20)
        }
    }
}