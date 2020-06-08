package Action

import Power.AbstractEnergyPower
import Relic.AbstractEnergyRelic
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class ChangeEnergyAction(var energyCount:Int):AbstractGameAction() {
    override fun update() {
        AbstractDungeon.player.powers.forEach {
            if (it is AbstractEnergyPower){
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