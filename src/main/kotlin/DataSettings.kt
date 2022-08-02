package com.pkgho.hoige.bot

import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.steamuser.SteamIDs
import com.pkgho.hoige.bot.whitelists.Config
import com.pkgho.hoige.bot.whitelists.EnableGroup

object DataSettings {
    fun setup(){
        Config.reload()
        EnableGroup.reload()
        SteamIDs.reload()
    }

    fun close(){
        Config.reload()
        Config.save()
        EnableGroup.save()
        SteamIDs.reload()
        SteamIDs.save()
    }

}