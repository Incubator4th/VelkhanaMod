package action

import character.Velkhana
import power.AbstractEnergyPower
import relic.AbstractEnergyRelic
import stance.IceBreathLow
import stance.IceBreathMedium
import stance.IceBreathNormal
import stance.IceBreathTop
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class ChangeEnergyAction(var energyCount: Int) : AbstractGameAction() {
    override fun update() {
        val velkhana = AbstractDungeon.player as Velkhana
        velkhana.stance.onExitStance()
        velkhana.energyCount = energyCount
        velkhana.stance = when (energyCount) {
            in 0..15 -> IceBreathNormal()
            in 16..30 -> IceBreathLow()
            in 31..40 -> IceBreathMedium()
            in 41..50 -> IceBreathTop()
            else -> {
                energyCount = 0
                IceBreathNormal()
            }
        }
        AbstractDungeon.player.powers.forEach {
            if (it is AbstractEnergyPower) {
                (it as AbstractEnergyPower).onEnergyChange(energyCount)
            }
        }
        AbstractDungeon.player.relics.forEach {
            if (it is AbstractEnergyRelic) {
                (it as AbstractEnergyRelic).onEnergyChange(energyCount)
            }
        }
        TODO("Not yet implemented")
    }

    init {

    }
}