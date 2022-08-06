package com.pkgho.hoige.bot

import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.steamuser.SteamIDs
import com.pkgho.hoige.bot.steamuser.Food
import com.pkgho.hoige.bot.whitelists.*

object DataSettings {
    fun setup(){
        Food.reload()
        Error.reload()
        Config.reload()
        EnableGroup.reload()
        SteamIDs.reload()
        TimeCache.reload()
        FoodCache.cache[1] = "goat"
        FoodCache.save()
        FoodCache.reload()
    }

    fun close(){
        FoodCache.cache.clear()
        TimeCache.save()
        FoodCache.save()
        Food.save()
        Config.save()
        Error.save()
        SteamIDs.save()
        EnableGroup.save()
        FoodCache.reload()
        TimeCache.reload()
        EnableGroup.reload()
        SteamIDs.reload()
        Error.reload()
        Config.reload()
        Food.reload()
    }

}