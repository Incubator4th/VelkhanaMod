package VelkhanaMod

import Character.Velkhana
import Character.VelkhanaEnum
import basemod.BaseMod
import basemod.interfaces.EditCharactersSubscriber
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
import com.megacrit.cardcrawl.core.CardCrawlGame

@SpireInitializer
class VelkhanaMod:EditCharactersSubscriber{
    init {
        BaseMod.subscribe(this)
    }

    override fun receiveEditCharacters() {
        BaseMod.addCharacter(Velkhana(CardCrawlGame.playerName),
                null,null,VelkhanaEnum.VELKHANA)
    }

    companion object {
        @JvmStatic
        fun initialize() {
            val mod = VelkhanaMod()
        }
    }
}