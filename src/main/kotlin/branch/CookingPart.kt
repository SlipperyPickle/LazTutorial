package branch

import Script
import leafs.*
import org.powbot.api.Tile
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldMakeDough(script: Script) : Branch<Script>(script, "ShouldMakeDough?") {
    override val successComponent: TreeComponent<Script> = MakeDough(script)
    override val failedComponent: TreeComponent<Script> = ShouldWalkToCookingExpert(script)

    override fun validate(): Boolean {

        return Varpbits.varpbit(281) == 150 && Inventory.stream().id(1929, 2516).toList().size == 2
    }
}

class ShouldWalkToCookingExpert(script: Script) : Branch<Script>(script, "ShouldWalkToCookingExpert?") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3075, 3084, 0), "Master Chef")
    override val failedComponent: TreeComponent<Script> = ShouldCookBread(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 120 || Varpbits.varpbit(281) == 140 ||
                Varpbits.varpbit(281) == 150
    }
}

class ShouldCookBread(script: Script) : Branch<Script>(script, "ShouldCookBread?") {
    //TODO check if works
    override val successComponent: TreeComponent<Script> = CookBread(script)
    override val failedComponent: TreeComponent<Script> = QuestPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 160
    }
}