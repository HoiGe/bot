package com.pkgho.hoige.bot.steamuser

import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content


object CommandHandler {
    var foods = Food.food[(0..Food.food.size).random()]
    suspend fun getMessage(){
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> { event ->
            val key = "#今天腐竹摸什么鱼"
            if (event.message.content==key){
                SendRequest().jsonHandler(group = event)
            }
            val food= "#Last今天吃什么"
            if (event.message.content==food){
                sender.group.sendMessage("今天Last吃 $foods")
                //TODO 任意用户名
            }
        }
    }
}