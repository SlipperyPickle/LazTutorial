package branch

import Script
import leafs.*
import org.powbot.api.Tile
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldWalkToAccountGuide(script: Script) : Branch<Script>(script, "ShouldWalkToAccountGuide") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3126, 3124, 0), "Account Guide")
    override val failedComponent: TreeComponent<Script> = ShouldOpenAccount(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 525 || Varpbits.varpbit(281) == 530 ||
                Varpbits.varpbit(281) == 532
    }
}

class ShouldOpenAccount(script: Script) : Branch<Script>(script, "ShouldOpenAccount") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.ACCOUNT_MANAGEMENT) // OpenAccount(script)
    override val failedComponent: TreeComponent<Script> = PrayerPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 531
    }
}