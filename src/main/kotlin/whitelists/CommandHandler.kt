package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot
import com.pkgho.hoige.bot.HoiBot.reload
import com.pkgho.hoige.bot.HoiBot.save
import com.pkgho.hoige.bot.whitelists.EnableGroup.enableGroups
import net.mamoe.mirai.console.command.*

class CommandHandler {
    object WHL : CompositeCommand(HoiBot, "whl") {
        @SubCommand("bind")
        suspend fun addGet(sender: CommandSender, ID: String) {
            enableGroups.forEach {
                if (it == sender.getGroupOrNull()?.id) {
                    MojangVerify.sendGet(key = ID, sender = sender)
                }
            }
        }

        @SubCommand("custom")
        suspend fun selfAddGet(sender: CommandSender, QID: Long, ID: String) {
            enableGroups.forEach{ io ->
                EnableGroup.activeAdmin.forEach {
                    if (io == sender.getGroupOrNull()?.id && it == sender.user?.id) {
                        AdminEdit.customUsers(sender = sender, ID = ID, QID = QID)
                    }
                }
            }
        }

            @SubCommand("delete")
            suspend fun deleteGet(sender: CommandSender, QID: Long) {
                enableGroups.forEach{ io ->
                    EnableGroup.activeAdmin.forEach {
                    if (io == sender.getGroupOrNull()?.id && it == sender.user?.id){
                        AdminEdit.deleteUsers(sender = sender, QID = QID)
                    }
                }
            }
        }

            @SubCommand("lookup")
            fun lookup(sender: CommandSender) {
                EnableGroup.activeAdmin.forEach {
                    if (it == sender.user?.id) {
                        println(sender.getGroupOrNull()?.id)
                    }
                }
            }
        }
        object Enable : SimpleCommand(HoiBot, "add-group") {
            @Handler
            suspend fun foo(sender: CommandSender, group: Long) {
                EnableGroup.activeAdmin.forEach {
                    if (it == sender.user?.id) {
                        enableGroups.add(group)
                        EnableGroup.save()
                        EnableGroup.reload()
                        sender.sendMessage("success")
                    }
                }
            }
        }
        object Disable : SimpleCommand(HoiBot, "rem-group") {
            @Handler
            suspend fun foo(sender: CommandSender, group: Long) {
                EnableGroup.activeAdmin.forEach {
                    if (it == sender.user?.id) {
                        enableGroups.remove(group)
                        EnableGroup.save()
                        EnableGroup.reload()
                        sender.sendMessage("success")
                    }
                }
            }
        }
    }

