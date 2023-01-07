package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Objects
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class MineCopper(script: Script) : Leaf<Script>(script, "MineCopper") {

    override fun execute() {

        val copperMine = Objects.stream().id(10079).nearest().first()
        if (!copperMine.inViewport()) {
            val copperTile = Tile(3084, 9502, 0)
            if (Players.local().distanceTo(copperTile) > 4) {
                Movement.moveTo(copperTile)
                Condition.wait({ !Players.local().inMotion() }, 300, 10)
            }
        }

        if (Inventory.stream().name("Copper ore").isEmpty()) {
            copperMine.interact("Mine", "Rocks")
            Condition.wait {
                Inventory.stream().name("Copper ore").isNotEmpty()
            }
        }
    }
}