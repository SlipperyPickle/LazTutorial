package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Objects
import org.powbot.api.script.tree.Leaf

class MineTin(script: Script) : Leaf<Script>(script, "Mine Tin") {

    override fun execute() {
        val tinMine = Objects.stream().id(10080).nearest().first()
        if (tinMine.inViewport()) {
            tinMine.interact("Mine", "Rocks")
            Condition.wait {
                Inventory.stream().name("Tin ore").isNotEmpty()
            }
        }
    }
}