package velkhanaMod

import character.Velkhana
import patch.AbstractCardEnum
import patch.VelkhanaEnum
import basemod.BaseMod
import basemod.interfaces.EditCharactersSubscriber
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.helpers.CardHelper
import org.apache.logging.log4j.LogManager

@SpireInitializer
class VelkhanaMod : EditCharactersSubscriber {
    init {
        BaseMod.subscribe(this)
        logger.info("add color ${AbstractCardEnum.VELKHANA_COLOR.toString()}")
        BaseMod.addColor(
            AbstractCardEnum.VELKHANA_COLOR,
            color, color, color, color, color, color, color,
            "", "", "", "", "", "", "", ""
        )
    }


    override fun receiveEditCharacters() {
        logger.info(VelkhanaEnum.VELKHANA.toString())
        logger.info(CardCrawlGame.playerName)
        BaseMod.addCharacter(
            Velkhana(CardCrawlGame.playerName),
            "image/button.png", "image/test.jpg", VelkhanaEnum.VELKHANA
        )
    }

    companion object {
        val logger = LogManager.getLogger(VelkhanaMod::class.java.name)
        val color = CardHelper.getColor(127, 217, 240)

        @JvmStatic
        fun initialize() {
            @SuppressWarnings("unused")
            val mod = VelkhanaMod()
        }
    }
}