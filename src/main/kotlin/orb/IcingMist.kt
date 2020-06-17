package orb

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.helpers.ImageMaster
import com.megacrit.cardcrawl.localization.OrbStrings
import com.megacrit.cardcrawl.orbs.AbstractOrb

class IcingMist : AbstractOrb() {


    init {
        this.ID = ORB_ID
        if (orbString != null) {
            this.name = orbString.NAME
        }
        baseEvokeAmount = 10
        evokeAmount = baseEvokeAmount
        basePassiveAmount = 10
        passiveAmount = basePassiveAmount
        updateDescription()
        channelAnimTimer = 0.5f
    }

    override fun applyFocus() {
        val power = AbstractDungeon.player.getPower("Focus")
        if (power != null && ID != "Plasma") {
            passiveAmount = Math.max(0, basePassiveAmount + power.amount)
            evokeAmount = Math.max(0, baseEvokeAmount + power.amount)
        } else {
            passiveAmount = basePassiveAmount
            evokeAmount = baseEvokeAmount
        }
    }

    override fun playChannelSFX() {
        CardCrawlGame.sound.play("ORB_FROST_CHANNEL", 0.1f)
    }

    override fun onEvoke() {}

    override fun makeCopy(): AbstractOrb {
        return IcingMist()
    }

    override fun render(sb: SpriteBatch?) {
        if (sb != null) {
            sb.setColor(c)
            sb.draw(
                ImageMaster.FROST_ORB_RIGHT,
                cX - 48.0f + bobEffect.y / 4.0f,
                cY - 48.0f + bobEffect.y / 4.0f,
                48.0f,
                48.0f,
                96.0f,
                96.0f,
                scale,
                scale,
                0.0f,
                0,
                0,
                96,
                96,
                hFlip1,
                false
            )

            sb.draw(
                ImageMaster.FROST_ORB_LEFT,
                cX - 48.0f + bobEffect.y / 4.0f,
                cY - 48.0f - bobEffect.y / 4.0f,
                48.0f,
                48.0f,
                96.0f,
                96.0f,
                scale,
                scale,
                0.0f,
                0,
                0,
                96,
                96,
                hFlip1,
                false
            )
            sb.draw(
                ImageMaster.FROST_ORB_MIDDLE,
                cX - 48.0f - bobEffect.y / 4.0f,
                cY - 48.0f + bobEffect.y / 2.0f,
                48.0f,
                48.0f,
                96.0f,
                96.0f,
                scale,
                scale,
                0.0f,
                0,
                0,
                96,
                96,
                hFlip2,
                false
            )
            renderText(sb)
            hb.render(sb)
        }

    }

    override fun updateDescription() {
        applyFocus()
        description = "${DESC[0]}$passiveAmount${DESC[1]}$evokeAmount ${DESC[2]}"
    }

    companion object {
        const val ORB_ID = "IcingMist"
        private val orbString: OrbStrings? = CardCrawlGame.languagePack.getOrbString("Empty")
        val DESC: Array<String> = orbString?.DESCRIPTION ?: arrayOf<String>()
        private val hFlip1 = MathUtils.randomBoolean()
        private val hFlip2 = MathUtils.randomBoolean()
        private const val vfxTimer = 1.0f
        private const val vfxIntervalMin = 0.15f
        private const val vfxIntervalMax = 0.8f
    }
}