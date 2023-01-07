package branch

import Script
import leafs.ChangeGender
import leafs.ChangeLooks
import leafs.OpenTab
import leafs.WalkAndTalk
import org.powbot.api.Tile
import org.powbot.api.rt4.Game
import org.powbot.api.rt4.Varpbits
import org.powbot.api.rt4.Widgets
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class CharacterScreen(script: Script) : Branch<Script>(script, "Character Screen?") {

    override val successComponent: TreeComponent<Script> = ShouldChangeGender(script)
    override val failedComponent: TreeComponent<Script> = ShouldTalkToGielinorGuide(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 1 //TODO && Character screen open
    }
}

class ShouldChangeGender(script: Script) : Branch<Script>(script, "Should Change Gender?") {

    override val successComponent: TreeComponent<Script> = ChangeGender(script)
    override val failedComponent: TreeComponent<Script> = ShouldChangeLooks(script)

    override fun validate(): Boolean {
        return if (Varpbits.varpbit(281) == 1 && Widgets.widget(679).valid())
            (script.settings.gender && Widgets.widget(679).component(66).actions()[0] == "") ||
                    (!script.settings.gender && Widgets.widget(679).component(65).actions()[0] == "")
        else false
    }
}

class ShouldChangeLooks(script: Script) : Branch<Script>(script, "ShouldChangeLooks?") {

    override val successComponent: TreeComponent<Script> = ChangeLooks(script)
    override val failedComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3093, 3107, 0), "Gielinor Guide")

    override fun validate(): Boolean {

        return script.settings.randomLooks && Varpbits.varpbit(281) == 1
    }
}

class ShouldTalkToGielinorGuide(script: Script) : Branch<Script>(script, "ShouldTalkToGielinorGuide?") {

    override val successComponent: TreeComponent<Script> = WalkAndTalk(script, Tile(3094, 3107, 0), "Gielinor Guide")
    override val failedComponent: TreeComponent<Script> = ShouldOpenSettings(script)

    override fun validate(): Boolean {

        return Varpbits.varpbit(281) == 2 || Varpbits.varpbit(281) == 7

    }
}

class ShouldOpenSettings(script: Script) : Branch<Script>(script, "ShouldOpenSettings?") {

    override val successComponent: TreeComponent<Script> = OpenTab(script, Game.Tab.SETTINGS)
    override val failedComponent: TreeComponent<Script> = GathererPart(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(281) == 3

    }
}