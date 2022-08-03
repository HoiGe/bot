package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot
import nl.vv32.rcon.Rcon

object RconSender {
    suspend fun addList(key: String) {
        Rcon.open("localhost", 25575).use { rcon ->
            if (rcon.authenticate("Sb97!**")) {
                println(rcon.sendCommand("whitelist add $key"))
            } else {
                HoiBot.vot.getFriend(2402210783)?.sendMessage("[WHL-RCON]玩家${key}添加失败")
                Error.error[key] = "ADD"
            }
        }
    }

    suspend fun remList(key: String) {
        Rcon.open("localhost", 25575).use { rcon ->
            if (rcon.authenticate("Sb97!**")) {
                println(rcon.sendCommand("whitelist remove $key"))
            } else {
                HoiBot.vot.getFriend(2402210783)?.sendMessage("[WHL-RCON]玩家${key}删除失败")
                Error.error[key] = "DEL"
            }
        }
    }
}