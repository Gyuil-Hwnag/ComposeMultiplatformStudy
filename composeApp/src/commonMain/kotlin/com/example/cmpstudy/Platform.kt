package com.example.cmpstudy

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform