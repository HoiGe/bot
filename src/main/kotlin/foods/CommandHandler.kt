package com.pkgho.hoige.bot.foods

import com.pkgho.hoige.bot.HoiBot
import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.foods.FoodCache.cache
import com.pkgho.hoige.bot.whitelists.EnableGroup
import kotlinx.coroutines.delay
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CommandHandler {
    fun food() {
        GlobalEventChannel
            .filterIsInstance<GroupMessageEvent>()
            .filter { EnableGroup.enableGroups.contains(it.group.id) }
            .subscribeAlways<GroupMessageEvent> {
                val input = it.message.contentToString()
                val rule = Regex("^#*|今天吃什么*$").replace(input, "")
                val gankspace = Regex("\\s").replace(rule, "")
                val sharp = Regex("""^#""").containsMatchIn(input)
                val text = Regex("""今天吃什么$""").containsMatchIn(input)
                if (getCurrentTimeStamp() - TimeCache.time >= TimeCache.delay && sharp && text) {
                    TimeCache.time = getCurrentTimeStamp()
                    TimeCache.save()
                    TimeCache.reload()
                    if (rule == "" || rule == "我") {
                        sendMessage(rule = "你", it = it)
                    } else {
                        sendCustomMessage(rule = gankspace, it = it)
                    }
                }

            }
    }

    private fun getCurrentTimeStamp(): Int {
        val times = System.currentTimeMillis()
        return (times / 1000).toInt()
    }

    suspend fun getTime() {
        while (true) {
            if (LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")) == "000000") {
                HoiBot.logger.info("数据已重置")
                cache.clear()
                cache["goat"] = 1
                FoodCache.save()
                FoodCache.reload()
                delay(10001)
            }
        }
    }

    private suspend fun sendCustomMessage(rule: String, it: GroupMessageEvent) {
        if (rule.length <= 16 && rule != "null") {
            if (rule.contains("[动画表情]", ignoreCase = true) || rule.contains("[图片]", ignoreCase = true)) {
                return
            } else {
                if (cache.isNotEmpty()) {
                    if (cache.containsKey(rule)) {
                        it.group.sendMessage("${rule}今天吃${Food.food[cache.getValue(rule)]}")
                    } else {
                        cache[rule] = (1 until Food.food.size).random()
                        FoodCache.save()
                        FoodCache.reload()
                        it.group.sendMessage("${rule}今天吃${Food.food[cache.getValue(rule)]}")
                    }
                }
            }
        }
    }

    private suspend fun sendMessage(rule: String, it: GroupMessageEvent) {
        if (cache.isNotEmpty()) {
            if (rule == "你" && cache.containsKey(it.sender.id.toString())) {
                    it.group.sendMessage("你今天吃${Food.food[cache.getValue(it.sender.id.toString())]}")
            } else {
                cache[it.sender.id.toString()] = (1 until Food.food.size).random()
                FoodCache.save()
                FoodCache.reload()
                it.group.sendMessage("${rule}今天吃${Food.food[cache.getValue(it.sender.id.toString())]}")
            }
        }
    }
}
