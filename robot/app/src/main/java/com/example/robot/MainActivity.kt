package com.example.robot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class MainActivity: AppCompatActivity() {
    private lateinit var redImage: ImageView
    private lateinit var whiteImage: ImageView
    private lateinit var yellowImage: ImageView
    private var turnCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redImage = findViewById(R.id.largeRedRobot)
        whiteImage = findViewById(R.id.largeWhiteRobot)
        yellowImage = findViewById(R.id.largeYellowRobot)
        redImage.setOnClickListener{view: View ->
            toggleImage()
        }
        whiteImage.setOnClickListener{view: View ->
            toggleImage()
        }
        yellowImage.setOnClickListener{view: View ->
            toggleImage()
        }
    }

    // red becomes large first, then white becomes large second, then yellow the last
    private fun toggleImage() {
        turnCount++
        if (turnCount >= 3) {
            turnCount = 0
        }
        if (turnCount == 1) {
            // red is large
            redImage.setImageResource(R.drawable.king_of_detroit_robot_red_large)
            whiteImage.setImageResource(R.drawable.king_of_detroit_robot_white_small)
            yellowImage.setImageResource(R.drawable.king_of_detroit_robot_yellow_small)
        } else if (turnCount == 2) {
            // white is large
            redImage.setImageResource(R.drawable.king_of_detroit_robot_red_small)
            whiteImage.setImageResource(R.drawable.king_of_detroit_robot_white_large)
            yellowImage.setImageResource(R.drawable.king_of_detroit_robot_yellow_small)
        } else {
            // yellow is large
            redImage.setImageResource(R.drawable.king_of_detroit_robot_red_small)
            whiteImage.setImageResource(R.drawable.king_of_detroit_robot_white_small)
            yellowImage.setImageResource(R.drawable.king_of_detroit_robot_yellow_large)
        }
    }
}
