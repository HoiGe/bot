plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.12.0"
    kotlin("kapt") version "1.7.10"
}

group = "com.pkgho.hoige.bot"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("nl.vv32.rcon:rcon:1.2.0")
    implementation ("com.github.kittinunf.fuel:fuel:2.3.1")
    implementation ("com.github.kittinunf.fuel:fuel-coroutines:2.3.1")
    implementation ("com.beust:klaxon:5.5")
}


