package branch

import Script
import leafs.*
import org.powbot.api.Tile
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldWalkToSmithingGuide(script: Script) : Branch<Script>(script, "ShouldWalkToSmithingGuide") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3080, 9502, 0), "Mining Instructor")
    override val failedComponent: TreeComponent<Script> = ShouldMineTin(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 260 || Varpbits.varpbit(281) == 330 ||
                Varpbits.varpbit(281) == 270
    }
}

class ShouldMineTin(script: Script) : Branch<Script>(script, "ShouldMineTin") {
    override val successComponent: TreeComponent<Script> = MineTin(script)
    override val failedComponent: TreeComponent<Script> = ShouldMineCopper(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 300 && Inventory.stream().name("Tin ore").isEmpty()
    }
}

class ShouldMineCopper(script: Script) : Branch<Script>(script, "ShouldMineCopper") {
    override val successComponent: TreeComponent<Script> = MineCopper(script)
    override val failedComponent: TreeComponent<Script> = ShouldSmelt(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 310
    }
}

class ShouldSmelt(script: Script) : Branch<Script>(script, "ShouldSmelt") {
    override val successComponent: TreeComponent<Script> = SmeltBronze(script)
    override val failedComponent: TreeComponent<Script> = ShouldSmith(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 320
    }
}

class ShouldSmith(script: Script) : Branch<Script>(script, "ShouldSmith") {
    override val successComponent: TreeComponent<Script> = SmithDagger(script)
    override val failedComponent: TreeComponent<Script> = CombatPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 340 || Varpbits.varpbit(281) == 350
    }
}