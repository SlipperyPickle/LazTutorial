package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Objects
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class EnterDungeon(script: Script) : Leaf<Script>(script, "EnterDungeon") {

    override fun execute() {
        val ladder = Objects.stream().id(9726)
        if (ladder.isNotEmpty()) {
            ladder.first().interact("Climb-down")
        }
        Condition.sleep(Random.nextInt(500, 600))
    }

}