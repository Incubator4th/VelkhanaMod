package power

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect
import com.megacrit.cardcrawl.actions.animations.VFXAction
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction
import com.megacrit.cardcrawl.actions.utility.SFXAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.AbstractPower
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect

class RimeFall(owner:AbstractCreature, amount:Int):AbstractPower() {

    init {
        this.owner = owner
        this.amount = amount
        updateDescription()
        loadRegion("thousandCuts")
    }

    override fun onAfterCardPlayed(usedCard: AbstractCard?) {
        flash()
        addToBot(SFXAction("ATTACK_HEAVY"))
        if (Settings.FAST_MODE) {
            addToBot(VFXAction(CleaveEffect()))
        } else {
            addToBot(VFXAction(owner, CleaveEffect(), 0.2f))
        }

        addToBot(
            DamageAllEnemiesAction(
                owner,
                DamageInfo.createDamageMatrix(amount, true),
                DamageType.THORNS,
                AttackEffect.NONE,
                true
            )
        )
    }

    override fun stackPower(stackAmount: Int) {
        fontScale = 8.0f
        amount += stackAmount
    }

    override fun updateDescription() {
        description = "${DESCRIPTIONS[0]}$amount${DESCRIPTIONS[1]}"
    }

    companion object {
        private const val POWER_ID = "RimeFall"
        private val powerStrings: PowerStrings? = CardCrawlGame.languagePack?.getPowerStrings(POWER_ID)
        val NAME: String? = powerStrings?.NAME
        val DESCRIPTIONS: Array<String> = powerStrings?.DESCRIPTIONS ?: arrayOf<String>()
        private const val justApplied = false
        private const val EFFECTIVENESS = 1.5f
        private const val EFFECTIVENESS_STRING = 50
    }
}