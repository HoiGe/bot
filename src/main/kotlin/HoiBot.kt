package com.pkgho.hoige.bot

import com.pkgho.hoige.bot.DataSettings.close
import com.pkgho.hoige.bot.DataSettings.setup
import com.pkgho.hoige.bot.foods.CommandHandler
import com.pkgho.hoige.bot.whitelists.ExistChecker
import kotlinx.coroutines.*
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
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
    @OptIn(DelicateCoroutinesApi::class)
    override fun onEnable() {
        setup()
        logger.info { "Plugin loaded" }
        GlobalScope.async {
            CommandRegister().commandManager()
            ExistChecker.quitRemove()
            CommandHandler.food()
            CommandHandler.getTime()
        }
    }

    override fun onDisable() {
        close()
        CommandRegister().unCommandManager()
    }
}

