package stance

import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.stances.AbstractStance

class IceBreathTop:AbstractStance() {
    override fun updateDescription() {
        TODO("Not yet implemented")
    }

    override fun atDamageGive(damage: Float, type: DamageInfo.DamageType?): Float {
        return super.atDamageGive(damage, type) * 1.25f
    }
}