package com.hectormtz.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice(R.id.dice1)
            rollDice(R.id.dice2)
        }
    }

    private fun rollDice(num: Int) {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val resultTextView: TextView = findViewById(num)
        resultTextView.text = diceRoll.toString()
    }
}

class Dice(private val numSides: Int = 6) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}