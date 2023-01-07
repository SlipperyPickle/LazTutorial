package branch

import Script
import leafs.*
import org.powbot.api.Tile
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldWalkToPrayer(script: Script) : Branch<Script>(script, "ShouldWalkToPrayer") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3123, 3106, 0), "Brother Brace")
    override val failedComponent: TreeComponent<Script> = ShouldOpenPrayer(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 540 || Varpbits.varpbit(281) == 550 ||
                Varpbits.varpbit(281) == 570 || Varpbits.varpbit(281) == 600
    }
}

class ShouldOpenPrayer(script: Script) : Branch<Script>(script, "ShouldOpenPrayer") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.PRAYER)//OpenPrayer(script)
    override val failedComponent: TreeComponent<Script> = ShouldOpenFriends(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 560
    }
}

class ShouldOpenFriends(script: Script) : Branch<Script>(script, "ShouldOpenFriends") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.FRIENDS_LIST)//OpenFriends(script)
    override val failedComponent: TreeComponent<Script> = MagePart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 580
    }
}