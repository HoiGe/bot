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
//            if ( it.key == sender.user?.id) {
//                if (num == count) {
//                    sender.sendMessage("[WHL]你已经登记了一个用户名 输入 \'/whl remove\' 以解绑")
//                }
//                        sender.sendMessage("[WHL]你输入的用户名已被占用")
//                                try {
//                                    RconSender.addList(key = key)
//                                    whiteList[sender.user!!.id] = key
//                                    Config.reload()
//                                    Config.save()
//                                    Config.reload()
//                                    sender.sendMessage("[WHL]添加成功")
//                                    HoiBot.logger.info("[WHL]已添加玩家 $key 至白名单")
//                                } catch (e: Exception) {
//                                    sender.sendMessage("[WHL]添加失败:连接已重置")
//                                    count = 0
//                                }
//                            }

    }

    suspend fun selfRemove(sender: CommandSender) {
        whiteList.forEach { List ->
            if (sender.user?.id == List.key) {
                whiteList.remove(sender.user!!.id)
                RconSender.remList(key = List.value)
                Config.reload()
                Config.save()
                Config.reload()
                sender.sendMessage("[WHL]账户已移除")
            }
        }
    }


    suspend fun quitRemove() {
        GlobalEventChannel
            .filterIsInstance<MemberLeaveEvent>()
            .filter { enableGroups.contains(it.group.id) }
            .subscribeAlways<MemberLeaveEvent> {
                whiteList.forEach {
                    if (this.member.id == it.key) {
                        if (this.member.id == it.key) {
                            whiteList.remove(this.member.id)
                            RconSender.remList(key = it.value)
                            HoiBot.logger.info { "[WHL]玩家${it.value}(${this.member.id})退出了群${this.group.name}" }
                            Config.reload()
                            Config.save()
                            Config.reload()
                        } else {
                            HoiBot.logger.info { "[WHL]未登记玩家${this.member.nick}(${this.member.id})退出了群${this.group.name}" }
                        }
                    }
                }
            }
    }
}