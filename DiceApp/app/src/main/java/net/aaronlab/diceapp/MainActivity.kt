package net.aaronlab.diceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupButton()
    }

    /*
    Setup Roll Button
     */
    private fun setupButton() {
        val rollButton = findViewById<Button>(R.id.btn_roll_dice)

        rollButton.setOnClickListener {

            rollDice()

        }
    }

    /*
    Roll Dice
     */
    private fun rollDice() {
        // Pick a Random Num
        val randomNumber = (1..6).random()

        // Change Text
        val randomNumberTxt = findViewById<TextView>(R.id.txt_random_number)
        randomNumberTxt.text = randomNumber.toString()
    }

}
