package com.example.intents

import android.app.Activity
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
private const val ROBOT_ENERGY = "com.example.intents.current_robot_energy"
private const val ROBOT_IMAGE_ID = "com.example.intents.current_robot_image_id"
const val REWARD = "com.example.intents.reward_chosen"

// constants
private const val NO_OF_REWARDS = 3

class RobotPurchase : AppCompatActivity() {
    private lateinit var rewardOneButton: Button
    private lateinit var rewardTwoButton: Button
    private lateinit var rewardThreeButton: Button
    private lateinit var rewardOneCost: TextView
    private lateinit var rewardTwoCost: TextView
    private lateinit var rewardThreeCost: TextView
    private lateinit var counterBox: TextView
    private lateinit var purchaseRobotImage: ImageView
    private var energyAvailable = 0

    private val rewards = mutableListOf(
        Reward(R.string.one, R.string.reward_a),
        Reward(R.string.two, R.string.reward_b),
        Reward(R.string.three, R.string.reward_c),
        Reward(R.string.four, R.string.reward_d),
        Reward(R.string.five, R.string.reward_e),
        Reward(R.string.six, R.string.reward_f),
        Reward(R.string.seven, R.string.reward_g),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot_purchase)

        rewardOneButton = findViewById(R.id.rewardOneButton)
        rewardTwoButton = findViewById(R.id.rewardTwoButton)
        rewardThreeButton = findViewById(R.id.rewardThreeButton)
        rewardOneCost = findViewById(R.id.rewardOneCost)
        rewardTwoCost = findViewById(R.id.rewardTwoCost)
        rewardThreeCost = findViewById(R.id.rewardThreeCost)
        counterBox = findViewById(R.id.counterBox)
        purchaseRobotImage = findViewById(R.id.purchaseRobot)

        // select random rewards
        val randomRewards = selectRewards()
        rewardOneButton.setText(randomRewards[0].letter)
        rewardTwoButton.setText(randomRewards[1].letter)
        rewardThreeButton.setText(randomRewards[2].letter)
        rewardOneCost.setText(randomRewards[0].amount)
        rewardTwoCost.setText(randomRewards[1].amount)
        rewardThreeCost.setText(randomRewards[2].amount)

        // get values from main activity, then set it
        energyAvailable = intent.getIntExtra(ROBOT_ENERGY, 0)
        counterBox.text = energyAvailable.toString()
        purchaseRobotImage.setImageResource(
            intent.getIntExtra(
                ROBOT_IMAGE_ID, R.drawable.king_of_detroit_robot_red_large
            )
        )

        rewardOneButton.setOnClickListener {
            makePurchase(
                getString(randomRewards[0].amount).toInt(), randomRewards[0].letter
            )
        }
        rewardTwoButton.setOnClickListener {
            makePurchase(
                getString(randomRewards[1].amount).toInt(), randomRewards[1].letter
            )
        }
        rewardThreeButton.setOnClickListener {
            makePurchase(
                getString(randomRewards[2].amount).toInt(), randomRewards[2].letter
            )
        }
    }

    // static method that can be invoked, for better encapsulation
    companion object {
        fun newIntent(packageContext: Context, robot: Robot): Intent {
            return Intent(packageContext, RobotPurchase::class.java).apply {
                // put certain values into this kvp
                putExtra(ROBOT_ENERGY, robot.energy)
                putExtra(ROBOT_IMAGE_ID, robot.robotLargeImageResource)
            }
        }
    }

    // sends back metadata to parent
    private fun setPurchaseValue(rewardId: Int) {
        val intent = Intent().apply {
            putExtra(REWARD, rewardId)
        }
        setResult(Activity.RESULT_OK, intent)
    }

    private fun selectRewards(): List<Reward> {
        return rewards.asSequence().shuffled().take(NO_OF_REWARDS).toList().sortedBy { it.letter }
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
            setPurchaseValue(rewardId)
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
