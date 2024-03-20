package com.example.kodecokmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform