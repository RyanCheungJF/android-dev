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

    private var turnCount = -1

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

        redImage = findViewById(R.id.redRobot)
        whiteImage = findViewById(R.id.whiteRobot)
        yellowImage = findViewById(R.id.yellowRobot)
        messageBox = findViewById(R.id.messageBox)
        robotImages = mutableListOf(redImage, whiteImage, yellowImage)

        redImage.setOnClickListener { toggleImage() }
        whiteImage.setOnClickListener { toggleImage() }
        yellowImage.setOnClickListener { toggleImage() }
    }

    private fun updateMessageBox() {
        messageBox.setText(robots[turnCount].robotMessageResource)
    }

    private fun setRobotTurn() {
        for (robot in robots) {
            robot.isRobotTurn = false
        }
        robots[turnCount].isRobotTurn = true
    }

    private fun setRobotImage() {
        for (i in 0..<robots.count()) {
            if (robots[i].isRobotTurn) {
                robotImages[i].setImageResource(robots[i].robotLargeImageResource)
            } else {
                robotImages[i].setImageResource(robots[i].robotSmallImageResource)
            }
        }
    }

    private fun toggleImage() {
        turnCount++
        if (turnCount >= 3) {
            turnCount = 0
        }
        updateMessageBox()
        setRobotTurn()
        setRobotImage()
    }
}
