package branch

import Script
import leafs.*
import org.powbot.api.Tile
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldTalkToQuestGuide(script: Script) : Branch<Script>(script, "ShouldTalkToQuestGuide") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3085, 3122, 0), "Quest Guide")
    override val failedComponent: TreeComponent<Script> = ShouldOpenQuestMenu(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 170 || Varpbits.varpbit(281) == 200 || Varpbits.varpbit(281) == 240
    }
}

class ShouldOpenQuestMenu(script: Script) : Branch<Script>(script, "ShouldEnterDungeon") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.QUESTS)
    override val failedComponent: TreeComponent<Script> = ShouldEnterDungeon(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 230
    }
}

class ShouldEnterDungeon(script: Script) : Branch<Script>(script, "ShouldEnterDungeon") {
    override val successComponent: TreeComponent<Script> = EnterDungeon(script)
    override val failedComponent: TreeComponent<Script> = SmithingPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 250
    }
}