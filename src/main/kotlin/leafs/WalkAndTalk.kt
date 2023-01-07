package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class WalkAndTalk(script: Script, private val tile: Tile, private val npcName: String) : Leaf<Script>(script, "Walking to $npcName") {

    override fun execute() {
        if (Players.local().distanceTo(tile) > 4) {
            Movement.moveTo(tile)
            Condition.wait{ Players.local().distanceTo(tile) < 4 }
        }
        if (npcName != "Banker") {
            for (i in 1..3) {
                while (!Chat.chatting()) {
                    Npcs.stream().name(npcName).nearest().first().click("Talk-to")

                    Condition.sleep(Random.nextInt(600, 750))
                }
            }

            if (Condition.wait({ Chat.chatting() }, 400, 5)) {
                while (Chat.canContinue()) {
                    Chat.continueChat()
                    Condition.sleep(Random.nextInt(650, 750))
                }
            }
        } else {
            Bank.open()
            Condition.sleep(Random.nextInt(300, 400))
            Bank.close()
        }
    }
}