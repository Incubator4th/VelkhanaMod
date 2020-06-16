package power

import card.AbstractIceCard
import character.Velkhana
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower

class EnergySlot : AbstractEnergyPower, AbstractPower() {
    override fun onEnergyChange(energyCount: Int) {
        this.amount = energyCount
    }

    override fun onGainedBlock(blockAmount: Float) {
        val velkhana = AbstractDungeon.player as Velkhana
        val energyChange: Int = when (blockAmount.toInt()) {
            in 5..15 -> velkhana.energyCount + 2
            in 16..25 -> velkhana.energyCount + 3
            in 26..Int.MAX_VALUE -> velkhana.energyCount + 5
            else -> velkhana.energyCount
        }
        velkhana.onEnergyChange(energyChange)
        super.onGainedBlock(blockAmount)
    }


    override fun onAfterCardPlayed(usedCard: AbstractCard?) {
        val velkhana = AbstractDungeon.player as Velkhana
        if (usedCard != null && usedCard.type == AbstractCard.CardType.ATTACK) {
            val energyChange = when (usedCard) {
                is AbstractIceCard -> velkhana.energyCount - 3 * usedCard.cost
                else -> velkhana.energyCount - 5 * usedCard.cost
            }
            velkhana.onEnergyChange(energyChange)
        }
        super.onAfterCardPlayed(usedCard)
    }
}