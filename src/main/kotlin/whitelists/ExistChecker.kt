package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot
import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.whitelists.Config.whiteList
import com.pkgho.hoige.bot.whitelists.EnableGroup.enableGroups
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.MemberLeaveEvent
import net.mamoe.mirai.utils.info

object ExistChecker {
    suspend fun existCheck(sender: CommandSender, key: String) {
//TODO 重置

    }

    suspend fun selfRemove(sender: CommandSender) {
//TODO 重置
//        whiteList.forEach { List ->
//            if (sender.user?.id == List.key) {
//                whiteList.remove(sender.user!!.id)
//                RconSender.remList(key = List.value)
//                Config.reload()
//                Config.save()
//                Config.reload()
//                sender.sendMessage("[WHL]账户已移除")
//            }
//        }
    }


    suspend fun quitRemove() {
        //TODO 重置
//        GlobalEventChannel
//            .filterIsInstance<MemberLeaveEvent>()
//            .filter { enableGroups.contains(it.group.id) }
//            .subscribeAlways<MemberLeaveEvent> {
//                whiteList.forEach {
//                    if (this.member.id == it.key) {
//                        if (this.member.id == it.key) {
//                            whiteList.remove(this.member.id)
//                            RconSender.remList(key = it.value)
//                            HoiBot.logger.info { "[WHL]玩家${it.value}(${this.member.id})退出了群${this.group.name}" }
//                            Config.reload()
//                            Config.save()
//                            Config.reload()
//                        } else {
//                            HoiBot.logger.info { "[WHL]未登记玩家${this.member.nick}(${this.member.id})退出了群${this.group.name}" }
//                        }
//                    }
//                }
//            }
    }
}