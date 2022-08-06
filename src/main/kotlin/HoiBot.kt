package com.pkgho.hoige.bot

import com.pkgho.hoige.bot.DataSettings.close
import com.pkgho.hoige.bot.DataSettings.setup
import com.pkgho.hoige.bot.steamuser.Food
import com.pkgho.hoige.bot.whitelists.EnableGroup
import com.pkgho.hoige.bot.whitelists.ExistChecker
import com.pkgho.hoige.bot.whitelists.FoodCache
import com.pkgho.hoige.bot.whitelists.FoodCache.cache
import kotlinx.coroutines.*
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.info
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
        val currentDateTime = LocalDateTime.now()
        val time = currentDateTime.format(DateTimeFormatter.ofPattern("HHmm"))
        var code = 0
        logger.info { "Plugin loaded" }
        GlobalScope.async {
            CommandRegister().commandManager()
            ExistChecker.quitRemove()
        }
        GlobalEventChannel
            .filterIsInstance<GroupMessageEvent>()
            .filter { EnableGroup.enableGroups.contains(it.group.id) }
            .subscribeAlways<GroupMessageEvent> {
                //val wolf = Regex("""wolf|loli|萝莉""",RegexOption.IGNORE_CASE) 娱乐功能 开关待做
                val rule = Regex("^#*|今天吃什么*$")
                val sharp = Regex("""^#""")
                val text = Regex("""今天吃什么$""")
                val matchedSharp = sharp.containsMatchIn(input = it.message.contentToString())
                val matchedText = text.containsMatchIn(input = it.message.contentToString())
                val resultString = rule.replace(it.message.contentToString(),"")
                if (matchedSharp && matchedText){
                    println(resultString)
                    if (time == "0000"){
                        cache.clear()
                        FoodCache.save()
                        FoodCache.reload()
                        HoiBot.logger.info { "[HoiFood]已清空用户缓存" }
                    }else{
                        //map.value 为用户名 map.key 为数值
                        try {
                            cache.forEach { map ->
                                if (resultString == map.value && resultString != "null") {
                                    it.group.sendMessage("${map.value}今天吃${Food.food[map.key]}")
                                } else {
                                    code++
                                    if (code == cache.size) {
                                        if (map.value != resultString && resultString != "null") {
                                            cache[(0 + 1  until  Food.food.size).random()] = resultString
                                            FoodCache.save()
                                            FoodCache.reload()
                                            cache.forEach { point ->
                                                if (resultString == point.value) {
                                                    it.group.sendMessage("${point.value}今天吃${Food.food[point.key]}")

                                                }
                                            }
                                        }
                                        code = 0
                                    }
                                }
                            }
                        }catch (e:Exception){
                            it.group.sendMessage("错误:输入的值不可用")
                        }
                    }
                }
//                val wolfd = wolf.containsMatchIn(input = it.message.contentToString())
//                if (wolfd){
//                    println("wccc")
//                    it.group.sendMessage("Loli_Wolf今天穿小裙子")
//                }
//                if (time == "0000")
            }
    }

    override fun onDisable() {
        close()
        CommandRegister().unCommandManager()
    }
}