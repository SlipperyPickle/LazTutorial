package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Objects
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class SmeltBronze(script: Script) : Leaf<Script>(script, "Smelt Bronze") {

    override fun execute() {
        val smeltTile = Tile(3079, 9498, 0)
        if (Players.local().distanceTo(smeltTile) > 4) {
            Movement.moveTo(smeltTile)
            Condition.wait({ !Players.local().inMotion() }, 300, 10)
        }

        val furnace = Objects.stream().id(10082)
        if (furnace.isNotEmpty()) {
            furnace.first().click() //.interact("Use", false)
        }

        Condition.wait({ !Inventory.stream().id(2349).isNotEmpty() }, 300, 10)
    }
}