package com.example.intents

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

// custom key for a value
const val EXTRA_ROBOT_ENERGY = "com.example.intents.current_robot_energy"

class RobotPurchase : AppCompatActivity() {
    private lateinit var rewardButtonOne: Button
    private lateinit var rewardButtonTwo: Button
    private lateinit var rewardButtonThree: Button
    private lateinit var counterBox: TextView
    private var energyAvailable = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robot_purchase)

        rewardButtonOne = findViewById(R.id.rewardButtonOne)
        rewardButtonTwo = findViewById(R.id.rewardButtonTwo)
        rewardButtonThree = findViewById(R.id.rewardButtonThree)
        counterBox = findViewById(R.id.counterBox)
        // get value from main activity, then set it
        energyAvailable = intent.getIntExtra(EXTRA_ROBOT_ENERGY, 0)
        counterBox.text = energyAvailable.toString()
    }

    // static method that can be invoked, for better encapsulation
    companion object {
        fun newIntent(packageContext: Context, energy: Int): Intent {
            return Intent(packageContext, RobotPurchase::class.java).apply {
                // put certain values into this kvp
                putExtra(EXTRA_ROBOT_ENERGY, energy)
            }
        }
    }
}
