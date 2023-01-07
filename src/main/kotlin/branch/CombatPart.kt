package branch

import Script
import leafs.*
import org.powbot.api.Tile
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class ShouldWalkToCombatGuide(script: Script) : Branch<Script>(script, "ShouldWalkToCombatGuide") {
    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3107, 9509, 0), "Combat Instructor")
    override val failedComponent: TreeComponent<Script> = ShouldEquipDagger(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 360 || Varpbits.varpbit(281) == 370 ||
                Varpbits.varpbit(281) == 410 || Varpbits.varpbit(281) == 470
    }
}

class ShouldEquipDagger(script: Script) : Branch<Script>(script, "ShouldEquipDagger") {
    override val successComponent: TreeComponent<Script> = EquipDagger(script)
    override val failedComponent: TreeComponent<Script> = ShouldOpenEquipment(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 405
    }
}

class ShouldOpenEquipment(script: Script) : Branch<Script>(script, "ShouldOpenEquipment") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.EQUIPMENT)
    override val failedComponent: TreeComponent<Script> = ShouldEquip(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 390 || Varpbits.varpbit(281) == 400 || Varpbits.varpbit(281) == 405
    }
}

class ShouldEquip(script: Script) : Branch<Script>(script, "ShouldEquip") {
    override val successComponent: TreeComponent<Script> = EquipWeapons(script)
    override val failedComponent: TreeComponent<Script> = ShouldOpenCombat(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 420
    }
}

class ShouldOpenCombat(script: Script) : Branch<Script>(script, "ShouldOpenCombat") {
    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.ATTACK) //OpenCombat(script)
    override val failedComponent: TreeComponent<Script> = ShouldMelee(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 430
    }
}

class ShouldMelee(script: Script) : Branch<Script>(script, "ShouldMelee") {
    override val successComponent: TreeComponent<Script> = Melee(script)
    override val failedComponent: TreeComponent<Script> = ShouldRange(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 440 || Varpbits.varpbit(281) == 450
    }
}

class ShouldRange(script: Script) : Branch<Script>(script, "ShouldRange") {
    override val successComponent: TreeComponent<Script> = Range(script)
    override val failedComponent: TreeComponent<Script> = BankPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 480 || Varpbits.varpbit(281) == 490
    }
}