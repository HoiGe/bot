package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.whitelists.Config.whiteList
import net.mamoe.mirai.console.command.CommandSender

object AdminEdit {
    suspend fun customUsers(sender: CommandSender, ID: String, QID: Long) {
        var code = 0
        try {
            RconSender.addList(key = ID)
        }catch (e:Exception){
            code = 1
            sender.sendMessage("[WHL-ADMIN]修改(${QID})的账户失败")
        }
        if (code == 0){
            Config.reload()
            whiteList[QID] = ID
            sender.sendMessage("[WHL-ADMIN]修改(${QID})的Minecraft账户为<${ID}>")
            Config.save()
            Config.reload()
        }
    }

    suspend fun deleteUsers(sender: CommandSender, QID: Long) {
        var count = 0
        whiteList.forEach { List ->
            if (QID == List.key) {
                var code = 0
                try {
                    RconSender.remList(key = List.value)
                }catch (e:Exception){
                    code = 1
                    sender.sendMessage("[WHL-ADMIN]移除(${QID})的账户失败")
                }
                if (code == 0){
                    Config.reload()
                    whiteList.remove(QID)
                    Config.save()
                    Config.reload()
                    sender.sendMessage("[WHL-ADMIN]已移除(${QID})的账户(${List.value})")
                }
            }else{
                count++
                if (count == whiteList.size){
                    sender.sendMessage("[WHL-ADMIN]此账户不存在")
                }
            }
        }
    }
}