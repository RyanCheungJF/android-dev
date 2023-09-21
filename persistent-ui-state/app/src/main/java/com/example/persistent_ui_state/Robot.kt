package com.example.persistent_ui_state

// data class used to hold state/ data
data class Robot(
    val robotMessageResource: Int,
    var isRobotTurn: Boolean,
    val robotLargeImageResource: Int,
    val robotSmallImageResource: Int
)
