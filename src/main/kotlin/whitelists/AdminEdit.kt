package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import net.mamoe.mirai.console.command.CommandSender

object AdminEdit {
    suspend fun customUsers(sender: CommandSender, ID: String, QID: Long) {
            Config.whiteList[QID] = ID
            RconSender.addList(key = ID)
            sender.sendMessage("[WHL-ADMIN]已设置(${QID})的Minecraft账户为<${ID}>")
            Config.save()
            Config.reload()
    }

    suspend fun deleteUsers(sender: CommandSender, QID: Long) {
        Config.whiteList.forEach { List ->
            if (QID == List.key) {
                if (QID == List.key) {
                    Config.whiteList.remove(QID)
                    RconSender.remList(key = List.value)
                    Config.save()
                    Config.reload()
                    sender.sendMessage("[WHL-ADMIN]已移除(${QID})的账户(${List.value})")
                }else{
                    sender.sendMessage("[WHL-ADMIN]此账户不存在")
                }
            }
        }
    }
}