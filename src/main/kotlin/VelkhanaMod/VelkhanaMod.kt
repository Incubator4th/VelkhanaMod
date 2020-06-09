package VelkhanaMod

import Character.Velkhana
import Patch.AbstractCardEnum
import Patch.VelkhanaEnum
import basemod.BaseMod
import basemod.interfaces.EditCharactersSubscriber
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.helpers.CardHelper
import com.megacrit.cardcrawl.helpers.CardLibrary
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
            "image/checkbox.png", "image/checkbox.png", VelkhanaEnum.VELKHANA
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