package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot
import net.mamoe.mirai.utils.info
import nl.vv32.rcon.Rcon

object RconSender {
    fun addList(key: String) {
        Rcon.open("localhost", 25575).use { rcon ->
            if (rcon.authenticate("Sb97!**")) {
                println(rcon.sendCommand("whitelist add $key"))
            } else {
                Error.error[key] = "ADD"
                HoiBot.logger.info { "[WHL]玩家${key}添加失败" }
            }
        }
    }
    fun remList(key: String) {
        Rcon.open("localhost", 25575).use { rcon ->
            if (rcon.authenticate("Sb97!**")) {
                println(rcon.sendCommand("whitelist remove $key"))
            } else {
                Error.error[key] = "DEL"
                HoiBot.logger.info { "[WHL]玩家${key}删除失败" }
            }
        }
    }
}