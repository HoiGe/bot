package com.pkgho.hoige.bot

import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.steamuser.SteamIDs
import com.pkgho.hoige.bot.steamuser.Food
import com.pkgho.hoige.bot.whitelists.Config
import com.pkgho.hoige.bot.whitelists.EnableGroup
import com.pkgho.hoige.bot.whitelists.Error

object DataSettings {
    fun setup(){
        Food.reload()
        Error.reload()
        Config.reload()
        EnableGroup.reload()
        SteamIDs.reload()
    }

    fun close(){
        SteamIDs.reload()
        Error.reload()
        Config.reload()
        Food.reload()
        Food.save()
        Config.save()
        Error.save()
        SteamIDs.save()
        EnableGroup.save()
    }

}