package com.example.persistent_ui_state

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var redImage: ImageView
    private lateinit var whiteImage: ImageView
    private lateinit var yellowImage: ImageView
    private lateinit var messageBox: TextView
    private lateinit var robotImages: MutableList<ImageView>

    private var turnCount = 0

    private val robots = listOf(
        Robot(
            R.string.red_robot_message,
            false,
            R.drawable.king_of_detroit_robot_red_large,
            R.drawable.king_of_detroit_robot_red_small
        ),
        Robot(
            R.string.white_robot_message,
            false,
            R.drawable.king_of_detroit_robot_white_large,
            R.drawable.king_of_detroit_robot_white_small
        ),
        Robot(
            R.string.yellow_robot_message,
            false,
            R.drawable.king_of_detroit_robot_yellow_large,
            R.drawable.king_of_detroit_robot_yellow_small
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageBox = findViewById(R.id.messageBox)
    }
}
