package com.example.robot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var redImage: ImageView
    private lateinit var whiteImage: ImageView
    private lateinit var yellowImage: ImageView
    private lateinit var leftButton: ImageButton
    private lateinit var rightButton: ImageButton
    private var turnCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redImage = findViewById(R.id.largeRedRobot)
        whiteImage = findViewById(R.id.largeWhiteRobot)
        yellowImage = findViewById(R.id.largeYellowRobot)
        leftButton = findViewById(R.id.leftButton)
        rightButton = findViewById(R.id.rightButton)
        leftButton.setOnClickListener {
            toggleImage(R.id.leftButton)
        }
        rightButton.setOnClickListener {
            toggleImage(R.id.rightButton)
        }
    }

    // red becomes large first, then white becomes large second, then yellow the last
    private fun toggleImage(id: Int) {
        if (id == R.id.leftButton) {
            turnCount++
        } else {
            turnCount--
        }

        if (turnCount >= 3) {
            turnCount = 0
        }
        if (turnCount < 0) {
            turnCount = 2
        }

        when (turnCount) {
            0 -> {
                // white is large
                redImage.setImageResource(R.drawable.king_of_detroit_robot_red_small)
                whiteImage.setImageResource(R.drawable.king_of_detroit_robot_white_large)
                yellowImage.setImageResource(R.drawable.king_of_detroit_robot_yellow_small)
            }

            1 -> {
                // red is large
                redImage.setImageResource(R.drawable.king_of_detroit_robot_red_large)
                whiteImage.setImageResource(R.drawable.king_of_detroit_robot_white_small)
                yellowImage.setImageResource(R.drawable.king_of_detroit_robot_yellow_small)
            }

            2 -> {
                // yellow is large
                redImage.setImageResource(R.drawable.king_of_detroit_robot_red_small)
                whiteImage.setImageResource(R.drawable.king_of_detroit_robot_white_small)
                yellowImage.setImageResource(R.drawable.king_of_detroit_robot_yellow_large)
            }
        }
    }
}
