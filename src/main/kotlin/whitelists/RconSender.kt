package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot
import nl.vv32.rcon.Rcon

object RconSender {
    suspend fun addList(key: String) {
        Rcon.open("localhost", 25575).use { rcon ->
            if (rcon.authenticate("password")) {
                println(rcon.sendCommand("whitelist add $key"))
            } else {
                HoiBot.logger.error("无法连接到Rcon服务器")
            }
        }
    }

    suspend fun remList(key: String) {
        Rcon.open("localhost", 25575).use { rcon ->
            if (rcon.authenticate("password")) {
                println(rcon.sendCommand("whitelist remove $key"))
            } else {
                HoiBot.logger.error("无法连接到Rcon服务器")
            }
        }
    }
}