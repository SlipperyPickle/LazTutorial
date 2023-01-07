package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.rt4.Equipment
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Leaf

class EquipDagger(script: Script) : Leaf<Script>(script, "EquipWeapons") {

    override fun execute() {
        Inventory.open()
        if (Equipment.stream().id(1171).isEmpty()) Inventory.stream().id(1205).first().interact("Wield")
        Condition.sleep(Random.nextInt(300, 450))
//        if (Equipment.stream().id(1277).isEmpty()) Inventory.stream().id(1277).first().click()
//        Condition.sleep(Random.nextInt(300, 450))
    }
}