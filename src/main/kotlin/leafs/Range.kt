package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.rt4.Npcs.stream
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random


class Range(script: Script) : Leaf<Script>(script, "Range") {

    override fun execute() {
        if (Game.tab(Game.Tab.INVENTORY)) {
            val bow = Inventory.stream().id(841)
            if (bow.isNotEmpty()) {
                bow.first().interact("Wield")
            }
            Condition.sleep(Random.nextInt(300, 450))
//            if (Equipment.stream().id(882).isEmpty()) Inventory.stream().id(882).first().click()
            val arrows = Inventory.stream().id(882)
            if (arrows.isNotEmpty()) {
                arrows.first().interact("Wield")
            }
        }
        val combatTile = Tile(3111, 9518, 0)
        if (Players.local().distanceTo(combatTile) > 4) {
            Movement.moveTo(combatTile)
            Condition.wait ({ !Players.local().inCombat() }, 400, 15)
        }

        val rats = stream().name("Giant rat").filtered { npc -> !npc.interacting().valid() }.nearest().list()
        if (rats.first().inViewport()) {
            rats.first().interact("Attack")
            Condition.wait ({
                Varpbits.varpbit(281) == 500
            }, 500, 50)
        }
        Condition.sleep(Random.nextInt(500, 700))
    }
}