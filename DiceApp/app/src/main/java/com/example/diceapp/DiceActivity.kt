package com.example.diceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.*

class DiceActivity : AppCompatActivity() {

    lateinit var diceImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)

        var btn = findViewById<Button>(R.id.roll)
        btn.text = getString(R.string.let_roll)

        btn.setOnClickListener{
            rollDice()
        }

        diceImage = findViewById(R.id.image)

    }

    private fun rollDice(){
        val random = Random().nextInt(6) + 1

        val drawableResource = when (random){
            1 -> R.drawable.dice1
            2 -> R.drawable.dice2
            3 -> R.drawable.dice3
            4 -> R.drawable.dice4
            5 -> R.drawable.dice5
            else -> R.drawable.dice6
        }
        diceImage.setImageResource(drawableResource)
    }
}