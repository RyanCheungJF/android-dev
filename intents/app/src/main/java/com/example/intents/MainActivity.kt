package com.example.intents

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private lateinit var redImage: ImageView
    private lateinit var whiteImage: ImageView
    private lateinit var yellowImage: ImageView
    private lateinit var messageBox: TextView
    private lateinit var rewardButton: Button
    private lateinit var robotImages: MutableList<ImageView>

    private val robotViewModel: RobotViewModel by viewModels()

    private val robots = listOf(
        Robot(
            R.string.red_robot_message,
            R.string.red_robot_purchase,
            R.drawable.king_of_detroit_robot_red_large,
            R.drawable.king_of_detroit_robot_red_small
        ), Robot(
            R.string.white_robot_message,
            R.string.white_robot_purchase,
            R.drawable.king_of_detroit_robot_white_large,
            R.drawable.king_of_detroit_robot_white_small
        ), Robot(
            R.string.yellow_robot_message,
            R.string.yellow_robot_purchase,
            R.drawable.king_of_detroit_robot_yellow_large,
            R.drawable.king_of_detroit_robot_yellow_small,
        )
    )

    // passes state back from child events
    private val purchaseLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { res ->
        if (res.resultCode == Activity.RESULT_OK) {
            val robotEnergy = res.data?.getIntExtra(ROBOT_ENERGY, 0)
            robots[robotViewModel.turnCount].energy = robotEnergy!!
            val rewardsPurchased = res.data?.getIntegerArrayListExtra(REWARDS_CHOSEN)
            val sb = StringBuilder()
            sb.append(getString(robots[robotViewModel.turnCount].robotPurchaseResource))
            sb.append(":\n")
            if (rewardsPurchased != null) {
                for (i in 0..<rewardsPurchased.size) {
                    sb.append(getString(rewardsPurchased[i]))
                    if (i < rewardsPurchased.size - 1) {
                        sb.append(", ")
                    }
                }
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        redImage = findViewById(R.id.redRobot)
        whiteImage = findViewById(R.id.whiteRobot)
        yellowImage = findViewById(R.id.yellowRobot)
        messageBox = findViewById(R.id.messageBox)
        rewardButton = findViewById(R.id.rewardButton)
        robotImages = mutableListOf(redImage, whiteImage, yellowImage)

        redImage.setOnClickListener { toggleImage() }
        whiteImage.setOnClickListener { toggleImage() }
        yellowImage.setOnClickListener { toggleImage() }
        rewardButton.setOnClickListener {
            if (robotViewModel.turnCount == -1) {
                robotViewModel.advanceCounter()
            }
            // tells activity manager to change the screen
            val intent = RobotPurchase.newIntent(this, robots[robotViewModel.turnCount])
            purchaseLauncher.launch(intent)
        }

        // if turn count is not the original value, use the pre existing state
        if (robotViewModel.turnCount != -1) {
            updateMessageBox()
            setRobotTurn()
            setRobotImage()
        }
    }

    private fun updateMessageBox() {
        messageBox.setText(robots[robotViewModel.turnCount].robotMessageResource)
    }

    private fun setRobotTurn() {
        for (robot in robots) {
            robot.isRobotTurn = false
        }
        robots[robotViewModel.turnCount].isRobotTurn = true
        robots[robotViewModel.turnCount].energy += 1
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
        robotViewModel.advanceCounter()
        updateMessageBox()
        setRobotTurn()
        setRobotImage()
    }
}
