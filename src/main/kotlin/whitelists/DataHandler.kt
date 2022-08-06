package com.pkgho.hoige.bot.whitelists

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("whitelist") {
    val whiteList: MutableMap<Long, String> by value()
}

object EnableGroup : AutoSavePluginConfig("config") {
    val enableGroups: MutableList<Long> by value()
    val activeAdmin: MutableList<Long> by value()
}
object Error : AutoSavePluginConfig("error_dump") {
    val error: MutableMap<String, String> by value()
}

object FoodCache : AutoSavePluginData("food_cache") {
    val cache: MutableMap<Int, String> by value()
}