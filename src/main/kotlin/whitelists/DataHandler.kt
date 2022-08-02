package com.pkgho.hoige.bot.whitelists

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("whitelist") {
    val whiteList: MutableMap<Long, String> by value()
}

object EnableGroup : AutoSavePluginConfig("config") {
    val enableGroups: MutableList<Long> by value()
    val activeAdmin: MutableList<Long> by value()
}