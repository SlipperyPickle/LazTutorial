package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class MagicCombat(script: Script) : Leaf<Script>(script, "MagicCombat") {

    override fun execute() {
        val combatTile = Tile(3139, 3091, 0)
        if (Players.local().distanceTo(combatTile) > 2) {
            Movement.moveTo(combatTile)
            Condition.wait{ !Players.local().inMotion() }
        }
        val chicken = Npcs.stream().id(3316).first()
        if (chicken.inViewport()) {

            Magic.Spell.WIND_STRIKE.cast()
            Condition.sleep(Random.nextInt(400, 600))
            chicken.click()
            Condition.sleep(Random.nextInt(400, 600))


        }

    }
}