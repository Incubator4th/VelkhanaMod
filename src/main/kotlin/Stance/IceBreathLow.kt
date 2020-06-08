package Stance

import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.stances.AbstractStance

class IceBreathLow:AbstractStance() {
    override fun updateDescription() {
        TODO("Not yet implemented")
    }

    override fun atDamageGive(damage: Float, type: DamageInfo.DamageType?): Float {
        return super.atDamageGive(damage, type) * 1.1f
    }
}