package com.hectormtz.diceroller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logging()
        division()

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            rollDice(R.id.dice1)
            rollDice(R.id.dice2)
        }

        rollDice(R.id.dice1)
        rollDice(R.id.dice2)
    }

    private fun rollDice(num: Int = R.id.dice1) {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(num)

        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()

    }

    fun division() {
        val numerator = 60
        var denominator = 4
        thread (start = true) {
            repeat(4) {
                Thread.sleep(4000L)
                Log.d(TAG, "$denominator")
                Log.v(TAG, "${numerator / denominator}")
                runOnUiThread {
                    findViewById<TextView>(R.id.division).setText("${numerator / denominator}")
                    denominator--
                }
            }
        }

    }
}

fun logging() {
    Log.e(TAG, "ERROR: a serious error like an app crash")
    Log.w(TAG, "WARN: warns about the potential for serious errors")
    Log.i(TAG, "INFO: reporting technical information, such as an operation succeeding")
    Log.d(TAG, "DEBUG: reporting technical information useful for debugging")
    Log.v(TAG, "VERBOSE: more verbose than DEBUG logs")
}
class Dice(private val numSides: Int = 6) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}