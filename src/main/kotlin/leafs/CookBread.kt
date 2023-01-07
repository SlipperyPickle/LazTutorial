package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Objects
import org.powbot.api.script.tree.Leaf

class CookBread(script: Script) : Leaf<Script>(script, "Cook Bread") {

    override fun execute() {
        val dough = Inventory.stream().id(2307)
        if (dough.isNotEmpty()) {
            dough.first().interact("Use")
        }
        if (Inventory.selectedItem().id() == dough.first().id) {
            Objects.stream().id(9736).nearest().first().interact("Use")
            Condition.wait ({ Inventory.stream().id(2307).isEmpty() }, 400, 20)
        }
    }
}