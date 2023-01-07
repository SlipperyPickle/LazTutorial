package branch

import Script
import leafs.OpenPollBooth
import leafs.WalkAndTalk
import org.powbot.api.Tile
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldWalkToBank(script: Script) : Branch<Script>(script, "ShouldWalkToBank") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3121, 3122, 0), "Banker")
    override val failedComponent: TreeComponent<Script> = ShouldOpenPollBooth(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 500 || Varpbits.varpbit(281) == 510
    }
}

class ShouldOpenPollBooth(script: Script) : Branch<Script>(script, "ShouldOpenPollBooth") {
    override val successComponent: TreeComponent<Script> = OpenPollBooth(script)
    override val failedComponent: TreeComponent<Script> = AccountPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 520
    }
}