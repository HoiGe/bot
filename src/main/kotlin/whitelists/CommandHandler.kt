package com.pkgho.hoige.bot.whitelists

import com.pkgho.hoige.bot.HoiBot
import com.pkgho.hoige.bot.whitelists.EnableGroup.enableGroups
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.getGroupOrNull

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

        @SubCommand("remove")
        suspend fun removeGet(sender: CommandSender) {
            enableGroups.forEach {
                if (it == sender.getGroupOrNull()?.id) {
                    ExistChecker.selfRemove(sender = sender)
                }
            }
        }

        @SubCommand("custom")
        suspend fun selfAddGet(sender: CommandSender, QID: Long, ID: String) {
            enableGroups.forEach{
                if (it == sender.getGroupOrNull()?.id){
                        EnableGroup.activeAdmin.forEach {
                            if (it == sender.user?.id) {
                                AdminEdit.customUsers(sender = sender, ID = ID, QID = QID)
                            }
                        }
                    }
                }
            }

            @SubCommand("delete")
            suspend fun deleteGet(sender: CommandSender, QID: Long) {
                enableGroups.forEach{
                    if (it == sender.getGroupOrNull()?.id){
                        EnableGroup.activeAdmin.forEach {
                            if (it == sender.user?.id) {
                                AdminEdit.deleteUsers(sender = sender, QID = QID)
                            }
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
    }

