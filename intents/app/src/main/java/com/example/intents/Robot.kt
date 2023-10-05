package com.example.intents

// data class used to hold state/ data
data class Robot(
    val robotMessageResource: Int,
    val robotPurchaseResource: Int,
    val robotLargeImageResource: Int,
    val robotSmallImageResource: Int,
    var isRobotTurn: Boolean = false,
    var energy: Int = 0,
)
