package leafs

import Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Chat
import org.powbot.api.rt4.Objects
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.Widgets.widget
import org.powbot.api.script.tree.Leaf
import kotlin.random.Random


class OpenPollBooth(script: Script) : Leaf<Script>(script, "OpenPollBooth") {

    override fun execute() {

        val pollBooth = Objects.stream().id(26801).nearest().first()
        if (pollBooth.inViewport()) {
            pollBooth.interact("Use")
            Condition.wait({ !Players.local().inMotion() }, 50, 50)
//            Objects.stream().id(9398).first().interact("Open", "Door")
        }

        Condition.wait { Chat.chatting() }

        if (Condition.wait({Chat.chatting()},400, 5)) {
            while (Chat.canContinue()) {
                Chat.continueChat()
                Condition.sleep(Random.nextInt(600, 750))
            }
        }
        val closeButton = widget(310).component(2).component(11)
        if (closeButton.visible()) closeButton.click()

        Condition.sleep(Random.nextInt(300, 500))
        widget(601).component(39).click()
    }
}