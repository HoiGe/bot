package com.pkgho.hoige.bot

import com.pkgho.hoige.bot.DataSettings.close
import com.pkgho.hoige.bot.DataSettings.setup
import com.pkgho.hoige.bot.steamuser.CommandHandler.getMessage
import com.pkgho.hoige.bot.whitelists.ExistChecker
import kotlinx.coroutines.*
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.utils.BotConfiguration
import net.mamoe.mirai.utils.info

object HoiBot : KotlinPlugin(
        JvmPluginDescription(
                id = "com.pkgho.hoige.bot",
                name = "HoiBot",
                version = "0.1.0",
        ) {
            author("HoiGe")
        }
) {
    val vot = BotFactory.newBot(3457850691, "20051110abc") {
        fileBasedDeviceInfo()
        heartbeatStrategy = BotConfiguration.HeartbeatStrategy.REGISTER
        protocol = BotConfiguration.MiraiProtocol.ANDROID_PAD
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onEnable() {
        setup()
        logger.info { "Plugin loaded" }
        val async = GlobalScope.async {
            vot.login()
            CommandRegister().commandManager()
            ExistChecker.quitRemove()
            getMessage()
        }
    }

    override fun onDisable() {
        close()
        CommandRegister().unCommandManager()
    }
}