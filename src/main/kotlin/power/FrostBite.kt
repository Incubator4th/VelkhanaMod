package power

import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.AbstractPower
import com.megacrit.cardcrawl.powers.VulnerablePower

class FrostBite(owner: AbstractCreature, amount: Int) : AbstractPower() {

    init {
        this.type = PowerType.DEBUFF
        this.isTurnBased = false
        updateDescription()
    }

    override fun atDamageReceive(damage: Float, damageType: DamageInfo.DamageType?): Float {
        return if (damageType == DamageType.NORMAL) {
            return damage * (1 + 0.1f * amount)
        } else {
            damage
        }
    }

    override fun updateDescription() {
        this.description = "${DESCRIPTIONS[0]}${10 * amount}${DESCRIPTIONS[1]}${DESCRIPTIONS[2]}"
    }

    companion object {
        const val POWER_ID = "ForstBite"
        private val powerStrings: PowerStrings? = CardCrawlGame.languagePack?.getPowerStrings(POWER_ID)
        val NAME: String? = powerStrings?.NAME
        val DESCRIPTIONS = powerStrings?.DESCRIPTIONS ?: arrayOf<String>()
        private const val justApplied = false
        private const val EFFECTIVENESS = 1.5f
        private const val EFFECTIVENESS_STRING = 50
    }
}