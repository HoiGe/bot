package com.pkgho.hoige.bot.whitelists


import net.mamoe.mirai.console.command.CommandSender
import com.github.kittinunf.fuel.Fuel

object MojangVerify {
    suspend fun sendGet(sender: CommandSender, key: String) {
        val r = Fuel.get("https://api.mojang.com/users/profiles/minecraft/$key")
        var code = r.response().second.statusCode
        if (code==200) {
            ExistChecker.existCheck(key = key, sender = sender)
        } else {
            sender.sendMessage("[MOJANG]:无法验证的用户名")
        }
    }
}