package com.pkgho.hoige.bot.steamuser

import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.console.data.AutoSavePluginConfig

object SteamIDs : AutoSavePluginConfig("steam-ids") {
    //Long GroupID | String SteamUID
    val ids: MutableMap<Long, String> by value()
}