package branch

import Script
import leafs.*
import org.powbot.api.Tile
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldWalkToGatherer(script: Script) : Branch<Script>(script, "ShouldWalkToGatherer?") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3102, 3095, 0), "Survival Expert")
    override val failedComponent: TreeComponent<Script> = ShouldOpenBackPack(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 10 || Varpbits.varpbit(281) == 20 ||
                Varpbits.varpbit(281) == 60

    }
}

class ShouldOpenBackPack(script: Script) : Branch<Script>(script, "ShouldOpenBackPack?") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.INVENTORY)
    override val failedComponent: TreeComponent<Script> = ShouldFish(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 30

    }
}

class ShouldFish(script: Script) : Branch<Script>(script, "ShouldFish?") {
    override val successComponent: TreeComponent<Script> = Fish(script)
    override val failedComponent: TreeComponent<Script> = ShouldOpenStats(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 40
    }
}

class ShouldOpenStats(script: Script) : Branch<Script>(script, "ShouldOpenStats?") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.STATS)
    override val failedComponent: TreeComponent<Script> = ShouldWoodcut(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 50
    }
}

class ShouldWoodcut(script: Script) : Branch<Script>(script, "ShouldWoodcut?") {
    override val successComponent: TreeComponent<Script> = WoodCut(script)
    override val failedComponent: TreeComponent<Script> = ShouldCookShrimps(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 70 || Varpbits.varpbit(281) == 80
    }
}

class ShouldCookShrimps(script: Script) : Branch<Script>(script, "ShouldCookShrimps?") {
    override val successComponent: TreeComponent<Script> = CookShrimps(script)
    override val failedComponent: TreeComponent<Script> = CookingPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 90
    }
}