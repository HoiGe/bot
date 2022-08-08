package com.pkgho.hoige.bot.foods

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

object FoodCache : AutoSavePluginData("food_cache") {
    val cache: MutableMap<String, Int> by value()
}

object TimeCache : AutoSavePluginData("time_cache"){
    var time: Int by value()
    val delay: Int by value()
}

object Food : AutoSavePluginConfig("foods") {
    val food: MutableList<String> by value()
}