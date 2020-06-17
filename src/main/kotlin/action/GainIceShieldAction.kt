package action

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect

class GainIceShieldAction : AbstractGameAction {
    constructor(target: AbstractCreature?, amount: Int) {
        this.target = target
        this.amount = amount
        actionType = ActionType.BLOCK
        duration = 0.25f
        startDuration = 0.25f
    }

    constructor(target: AbstractCreature?, source: AbstractCreature?, amount: Int) {
        this.setValues(target, source, amount)
        actionType = ActionType.BLOCK
        duration = 0.25f
        startDuration = 0.25f
    }

    constructor(target: AbstractCreature?, amount: Int, superFast: Boolean) : this(target, amount) {
        if (superFast) {
            startDuration = Settings.ACTION_DUR_XFAST
            duration = startDuration
        }
    }

    constructor(target: AbstractCreature?, source: AbstractCreature?, amount: Int, superFast: Boolean) : this(
        target,
        source,
        amount
    ) {
        if (superFast) {
            startDuration = Settings.ACTION_DUR_XFAST
            duration = startDuration
        }
    }

    override fun update() {
        if (!target.isDying && !target.isDead && duration == startDuration) {
            AbstractDungeon.effectList.add(FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.SHIELD))
            target.addBlock(amount)
            val var1: Iterator<*> = AbstractDungeon.player.hand.group.iterator()
            while (var1.hasNext()) {
                val c = var1.next() as AbstractCard
                c.applyPowers()
            }
        }
        tickDuration()
    }

    companion object {
        private const val DUR = 0.25f
    }
}