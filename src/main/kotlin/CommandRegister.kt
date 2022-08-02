package com.pkgho.hoige.bot

import net.mamoe.mirai.console.command.CommandManager
import com.pkgho.hoige.bot.whitelists.CommandHandler

class CommandRegister {
    fun commandManager() {
        CommandManager.registerCommand(CommandHandler.WHL)
    }

    fun unCommandManager() {
        CommandManager.unregisterAllCommands(HoiBot)
    }
}