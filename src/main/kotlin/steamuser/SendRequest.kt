package com.pkgho.hoige.bot.steamuser

import com.beust.klaxon.*
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.pkgho.hoige.bot.steamuser.SteamIDs.ids
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.event.events.GroupMessageEvent


class SendRequest() {
    //PersonState 0-离线 1-在线 3-离开
    suspend fun jsonHandler(group: GroupMessageEvent) {
        var count : Int = 0
        ids.forEach() { it ->
            if (it.key == group.group.id) {
                if (it.key == group.group.id) {
                    val steamID = it.value
                    main(steamID = steamID, group=group)
                }
            }else{
                count++
                if (count == ids.size){
                    group.subject.sendMessage("错误: 这个群没有腐竹(ノω<。)")
                }
            }
        }
    }

    private suspend fun main(steamID:String, group: GroupMessageEvent) {
        runBlocking {
            val (request, response, result) = Fuel.get("https://api.pkgho.com/steam/?uid=${steamID}")
                .awaitStringResponseResult()
            val (bytes, error) = result
            val parser: Parser = Parser.default()
            val stringBuilder: StringBuilder = StringBuilder(bytes.toString())
            val json: JsonObject = parser.parse(stringBuilder) as JsonObject
            val game = json.string("game")
            if (game != null) {
                group.subject.sendMessage("腐竹正在: $game")
            } else {
                group.subject.sendMessage("腐竹很忙哦(o-ωｑ))")
            }
        }
    }
}