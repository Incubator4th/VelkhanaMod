package VelkhanaMod

import Character.Velkhana
import Character.VelkhanaEnum
import basemod.BaseMod
import basemod.interfaces.EditCharactersSubscriber
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
import com.megacrit.cardcrawl.core.CardCrawlGame
import org.apache.logging.log4j.LogManager

@SpireInitializer
class VelkhanaMod:EditCharactersSubscriber{
    init {
        BaseMod.subscribe(this)
    }

    override fun receiveEditCharacters() {
        logger.info(VelkhanaEnum.VELKHANA.toString())
        logger.info(CardCrawlGame.playerName)
        BaseMod.addCharacter(Velkhana(CardCrawlGame.playerName),
                "img/test.jpg","img/test.jpg",VelkhanaEnum.VELKHANA)
    }

    companion object {
        val logger = LogManager.getLogger(VelkhanaMod::class.java.name)
        @JvmStatic
        fun initialize() {
            val mod = VelkhanaMod()
        }
    }
}