package com.pkgho.hoige.bot.steamuser

import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content


object CommandHandler {

    suspend fun getMessage(){
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> { event ->
            val key = "#今天腐竹摸什么鱼"
            if (event.message.content==key){
                SendRequest().jsonHandler(group = event)
            }
        }
    }
}