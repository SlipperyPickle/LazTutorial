package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Npcs
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class Melee(script: Script) : Leaf<Script>(script, "Melee") {

    override fun execute() {
        val combatTile = Tile(3108, 9518, 0)
        if (Players.local().distanceTo(combatTile) > 4) {
            Movement.moveTo(combatTile)
            Condition.wait{ !Players.local().inMotion() }
        }

        //val rat = Npcs.stream().id(3313).nearest().first()
        val rat = Npcs.stream().name("Giant rat").filtered { npc -> !npc.interacting().valid() }.nearest().first()
        if (rat.inViewport()) {
            rat.interact("Attack")
            Condition.wait ({
                Varpbits.varpbit(281) == 470
            }, 500, 50)
        }

        Condition.sleep(Random.nextInt(500, 700))
    }
}