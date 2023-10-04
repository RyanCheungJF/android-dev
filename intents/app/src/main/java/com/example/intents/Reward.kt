package com.example.intents

import java.io.Serializable

data class Reward(
    val amount: Int,
    val letter: Int
) : Serializable
