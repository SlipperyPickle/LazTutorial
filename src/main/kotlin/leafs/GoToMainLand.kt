package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random

class GoToMainLand(script: Script) : Leaf<Script>(script, "GoToMainland") {

    override fun execute() {

        val magicTile = Tile(3141, 3087, 0)
        if (Players.local().distanceTo(magicTile) > 4) {
            Movement.moveTo(magicTile)
            Condition.wait{ !Players.local().inMotion() }
        }

        for(i in 1..3) {
            while(!Chat.chatting()) {
                Npcs.stream().name("Magic Instructor").nearest().first().click("Talk-to")
                Condition.sleep(Random.nextInt(600, 750))
            }
        }

        if (Condition.wait({ Chat.chatting()},400, 5)) {
            while (Chat.chatting()) {
                if (!Chat.continueChat()) if (!Chat.continueChat("Yes."))
                    Chat.continueChat("No, I'm not planning to do that.")

                Condition.sleep(Random.nextInt(650, 750))
            }
        }

        val freshServer = Widgets.widget(788).component(26).component(0)
        val mainServer = Widgets.widget(788).component(40).component(0)

        Condition.wait { freshServer.visible() }

        if (freshServer.visible()) if (script.settings.freshWorld) freshServer.click()
        else mainServer.click()
        Condition.sleep(Random.nextInt(650, 750))
        val confirmButton = Widgets.widget(788).component(15).component(0)
        Condition.wait { confirmButton.visible() }
        confirmButton.click()
        Condition.sleep(Random.nextInt(650, 750))

    }
}