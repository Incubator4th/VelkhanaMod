package Character

import basemod.abstracts.CustomPlayer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.EnergyManager
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.screens.CharSelectInfo
import com.megacrit.cardcrawl.unlock.UnlockTracker
import java.util.ArrayList

class Velkhana(name: String?) : CustomPlayer(name, VelkhanaEnum.VELKHANA, null, null) {
    companion object {
        const val ENERGY_PER_TURN = 3 // how much energy you get every turn
        const val ORB_SLOTS = 0
        const val MY_CHARACTER_SHOULDER_2 = "img/char/shoulder2.png" // campfire pose
        const val MY_CHARACTER_SHOULDER_1 = "img/char/shoulder1.png" // another campfire pose
        const val MY_CHARACTER_CORPSE = "img/char/corpse.png" // dead corpse
        const val MY_CHARACTER_SKELETON_ATLAS = "img/char/skeleton.atlas" // spine animation atlas
        const val MY_CHARACTER_SKELETON_JSON = "img/char/skeleton.json" // spine animation json

        const val STARTING_HP = 75
        const val MAX_HP = 75
        const val STARTING_GOLD = 99
        const val HAND_SIZE = 5

    }

    init {
        this.dialogX = (this.drawX + 0.0f * Settings.scale)
        this.dialogY = (this.drawY + 0.0f * Settings.scale)
        initializeClass(null,
                MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1, MY_CHARACTER_CORPSE, loadout,
                20.0f, -10.0f, 220.0f, 290.0f, EnergyManager(ENERGY_PER_TURN))
    }

    override fun getStartingRelics(): ArrayList<String> {
        val retVal = arrayListOf<String>("MyRelic")
        retVal.forEach {
            UnlockTracker.markBossAsSeen(it)
        }
        return retVal
    }

    override fun getLoadout(): CharSelectInfo {
        return CharSelectInfo("Velkhana", "My character is a person from the outer worlds. He makes magic stuff happen.",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                this as AbstractPlayer, startingRelics,startingDeck, false)
    }

    fun onEnergySlotChange(energyCount:Int) {

    }

    override fun getSlashAttackColor(): Color {
        TODO("Not yet implemented")
    }

    override fun getAscensionMaxHPLoss(): Int {
        TODO("Not yet implemented")
    }

    override fun getCustomModeCharacterButtonSoundKey(): String {
        TODO("Not yet implemented")
    }

    override fun doCharSelectScreenSelectEffect() {
        TODO("Not yet implemented")
    }

    override fun getSpireHeartText(): String {
        TODO("Not yet implemented")
    }

    override fun getTitle(p0: PlayerClass?): String {
        TODO("Not yet implemented")
    }

    override fun getSpireHeartSlashEffect(): Array<AbstractGameAction.AttackEffect> {
        TODO("Not yet implemented")
    }

    override fun getLocalizedCharacterName(): String {
        TODO("Not yet implemented")
    }

    override fun getVampireText(): String {
        TODO("Not yet implemented")
    }

    override fun getPortraitImageName(): String {
        TODO("Not yet implemented")
    }

    override fun getCardTrailColor(): Color {
        TODO("Not yet implemented")
    }

    override fun getCardRenderColor(): Color {
        TODO("Not yet implemented")
    }

    override fun getCardColor(): AbstractCard.CardColor {
        TODO("Not yet implemented")
    }

    override fun getEnergyNumFont(): BitmapFont {
        TODO("Not yet implemented")
    }

    override fun getStartingDeck(): ArrayList<String> {
        return arrayListOf<String>("MyCard0", "MyCard0", "MyCard0", "MyCard0", "MyCard0")
    }

    override fun getStartCardForEvent(): AbstractCard {
        TODO("Not yet implemented")
    }

    override fun newInstance(): AbstractPlayer {
        return Velkhana(this.name)
    }

}