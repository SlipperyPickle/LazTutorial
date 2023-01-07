package branch

import Script
import leafs.*
import org.powbot.api.Tile
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldWalkToMagicGuide(script: Script) : Branch<Script>(script, "ShouldWalkToMagicGuide") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3141, 3087, 0), "Magic Instructor")
    override val failedComponent: TreeComponent<Script> = ShouldOpenMagic(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 610 || Varpbits.varpbit(281) == 620 ||
                Varpbits.varpbit(281) == 640
    }
}

class ShouldOpenMagic(script: Script) : Branch<Script>(script, "ShouldOpenMagic") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.MAGIC)
    override val failedComponent: TreeComponent<Script> = ShouldMagicCombat(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 630
    }
}

class ShouldMagicCombat(script: Script) : Branch<Script>(script, "ShouldMagicCombat") {
    override val successComponent: TreeComponent<Script> = MagicCombat(script)
    override val failedComponent: TreeComponent<Script> = ShouldGoToMainland(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 650
    }
}

class ShouldGoToMainland(script: Script) : Branch<Script>(script, "ShouldGoToMainland") {
    override val successComponent: TreeComponent<Script> = GoToMainLand(script)
    override val failedComponent: TreeComponent<Script> = StopPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 670
    }
}

