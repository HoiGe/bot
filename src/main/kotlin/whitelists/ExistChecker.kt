package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot
import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.whitelists.Config.whiteList
import com.pkgho.hoige.bot.whitelists.Error.error
import com.pkgho.hoige.bot.whitelists.EnableGroup.enableGroups
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.MemberLeaveEvent
import net.mamoe.mirai.utils.error
import net.mamoe.mirai.utils.info

object ExistChecker {
    suspend fun existCheck(sender: CommandSender, key: String) {
        var count = 0
        whiteList.forEach { List ->
            if (key == List.value){
                sender.sendMessage("[WHL]错误:用户名(${key})已绑定QQ账号(${List.key})")
            }else{
                count++
                var code = 0
                if (count == whiteList.size){
                    try {
                        RconSender.addList(key = List.value)
                    }catch (e:Exception){
                        sender.sendMessage("[WHL]用户名(${key})添加失败:内部错误")
                        code = 1
                    }finally{
                        if (code == 0){
                            if (sender.user?.id == List.key){
                                Config.reload()
                                sender.sendMessage("[WHL](${sender.user?.id})已从${List.value}换绑为${key}")
                                whiteList[sender.user!!.id] = key
                                Config.save()
                                Config.reload()
                            }else{
                                Config.reload()
                                sender.sendMessage("[WHL]用户名(${key})添加成功")
                                whiteList[sender.user!!.id] = key
                                Config.save()
                                Config.reload()
                            }
                        }
                    }
                }
            }
        }
    }

    fun quitRemove() {
        GlobalEventChannel
            .filterIsInstance<MemberLeaveEvent>()
            .filter { enableGroups.contains(it.group.id) }
            .subscribeAlways<MemberLeaveEvent> {
                var count = 0
                var code = 0
                whiteList.forEach {
                    if (this.member.id == it.key){
                        try {
                            RconSender.remList(key = it.value)
                        }catch (e:Exception){
                            HoiBot.logger.error { "[WHL]玩家${it.value}(${this.member.id})的退群(${this.group.name})处理失败" }
                            error[it.value] = "DEL"
                            code = 1
                        }
                        if (code == 0){
                            Config.reload()
                            whiteList.remove(this.member.id)
                            HoiBot.logger.info { "[WHL]玩家${it.value}(${this.member.id})退出了群${this.group.name}" }
                            Config.save()
                            Config.reload()
                        }
                    }else{
                        count++
                        if (count == whiteList.size){
                            HoiBot.logger.info { "[WHL]未登记玩家${this.member.nick}(${this.member.id})退出了群${this.group.name}" }
                        }
                    }
                }
            }

    }
}