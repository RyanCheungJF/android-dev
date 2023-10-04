package com.example.intents

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.text.StringBuilder

// custom key for a value, one being the energy and another being the robot image id
const val ROBOT_ENERGY = "com.example.intents.current_robot_energy"
const val ROBOT_IMAGE_ID = "com.example.intents.current_robot_image_id"

class RobotPurchase : AppCompatActivity() {
    private lateinit var rewardButtonOne: Button
    private lateinit var rewardButtonTwo: Button
    private lateinit var rewardButtonThree: Button
    private lateinit var counterBox: TextView
    private lateinit var purchaseRobotImage: ImageView
    private var energyAvailable = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot_purchase)

        rewardButtonOne = findViewById(R.id.rewardButtonOne)
        rewardButtonTwo = findViewById(R.id.rewardButtonTwo)
        rewardButtonThree = findViewById(R.id.rewardButtonThree)
        counterBox = findViewById(R.id.counterBox)
        purchaseRobotImage = findViewById(R.id.purchaseRobot)

        // get values from main activity, then set it
        energyAvailable = intent.getIntExtra(ROBOT_ENERGY, 0)
        counterBox.text = energyAvailable.toString()
        purchaseRobotImage.setImageResource(
            intent.getIntExtra(
                ROBOT_IMAGE_ID,
                R.drawable.king_of_detroit_robot_red_large
            )
        )

        rewardButtonOne.setOnClickListener { makePurchase(1, R.string.reward_a) }
        rewardButtonTwo.setOnClickListener { makePurchase(2, R.string.reward_b) }
        rewardButtonThree.setOnClickListener { makePurchase(3, R.string.reward_c) }
    }

    // static method that can be invoked, for better encapsulation
    companion object {
        fun newIntent(packageContext: Context, energy: Int, robotImageId: Int): Intent {
            return Intent(packageContext, RobotPurchase::class.java).apply {
                // put certain values into this kvp
                putExtra(ROBOT_ENERGY, energy)
                putExtra(ROBOT_IMAGE_ID, robotImageId)
            }
        }
    }

    private fun makePurchase(cost: Int, rewardId: Int) {
        if (energyAvailable < cost) {
            Toast.makeText(this, R.string.insufficient_energy, Toast.LENGTH_SHORT).show()
        } else {
            energyAvailable -= cost
            counterBox.text = energyAvailable.toString()
            val sb = StringBuilder()
            sb.append(getString(rewardId))
            sb.append(" ")
            sb.append(getString(R.string.purchased))
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
