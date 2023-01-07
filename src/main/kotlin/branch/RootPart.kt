package branch

import Script
import leafs.StopScript
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class CharacterPart(script: Script) : Branch<Script>(script, "CharacterPart") {
    override val successComponent: TreeComponent<Script> = CharacterScreen(script)
    override val failedComponent: TreeComponent<Script> = GathererPart(script)

    override fun validate(): Boolean {
        val varp : Int = Varpbits.varpbit(281)
        return (varp in 1..9)
    }
}

class GathererPart(script: Script) : Branch<Script>(script, "GathererPart") {
    override val successComponent: TreeComponent<Script> = ShouldWalkToGatherer(script)
    override val failedComponent: TreeComponent<Script> = CookingPart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 10..90)
    }
}

class CookingPart(script: Script) : Branch<Script>(script, "CookingPart") {
    override val successComponent: TreeComponent<Script> = ShouldMakeDough(script)
    override val failedComponent: TreeComponent<Script> = QuestPart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 120..160)
    }
}

class QuestPart(script: Script) : Branch<Script>(script, "QuestPart") {
    override val successComponent: TreeComponent<Script> = ShouldTalkToQuestGuide(script)
    override val failedComponent: TreeComponent<Script> = SmithingPart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 170..250)
    }
}

class SmithingPart(script: Script) : Branch<Script>(script, "SmithingPart") {
    override val successComponent: TreeComponent<Script> = ShouldWalkToSmithingGuide(script)
    override val failedComponent: TreeComponent<Script> = CombatPart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 260..350)
    }
}

class CombatPart(script: Script) : Branch<Script>(script, "CombatPart") {
    override val successComponent: TreeComponent<Script> = ShouldWalkToCombatGuide(script)
    override val failedComponent: TreeComponent<Script> = BankPart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 360..490)
    }
}

class BankPart(script: Script) : Branch<Script>(script, "BankPart") {
    override val successComponent: TreeComponent<Script> = ShouldWalkToBank(script)
    override val failedComponent: TreeComponent<Script> = AccountPart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 500..520)
    }
}

class AccountPart(script: Script) : Branch<Script>(script, "AccountPart") {
    override val successComponent: TreeComponent<Script> = ShouldWalkToAccountGuide(script)
    override val failedComponent: TreeComponent<Script> = PrayerPart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 525..532)
    }
}

class PrayerPart(script: Script) : Branch<Script>(script, "PrayerPart") {
    override val successComponent: TreeComponent<Script> = ShouldWalkToPrayer(script)
    override val failedComponent: TreeComponent<Script> = MagePart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 540..600)
    }
}

class MagePart(script: Script) : Branch<Script>(script, "MagePart") {

    override val successComponent: TreeComponent<Script> = ShouldWalkToMagicGuide(script)
    override val failedComponent: TreeComponent<Script> = StopPart(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp in 610..670)
    }
}

class StopPart(script: Script) : Branch<Script>(script, "StopPart") {

    override val successComponent: TreeComponent<Script> = StopScript(script)
    override val failedComponent: TreeComponent<Script> = StopScript(script)

    override fun validate(): Boolean {
        val varp = Varpbits.varpbit(281)
        return (varp == 1000)
    }
}