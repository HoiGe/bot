package com.pkgho.hoige.bot

import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.steamuser.SteamIDs
import com.pkgho.hoige.bot.steamuser.Food
import com.pkgho.hoige.bot.whitelists.Config
import com.pkgho.hoige.bot.whitelists.EnableGroup
import com.pkgho.hoige.bot.whitelists.Error
import com.pkgho.hoige.bot.whitelists.FoodCache

object DataSettings {
    fun setup(){
        Food.reload()
        Error.reload()
        Config.reload()
        EnableGroup.reload()
        SteamIDs.reload()

        FoodCache.cache[1] = "goat"
        FoodCache.save()
        FoodCache.reload()
    }

    fun close(){
        FoodCache.cache.clear()
        FoodCache.save()
        Food.save()
        Config.save()
        Error.save()
        SteamIDs.save()
        EnableGroup.save()
        FoodCache.reload()
        EnableGroup.reload()
        SteamIDs.reload()
        Error.reload()
        Config.reload()
        Food.reload()
    }

}