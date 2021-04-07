package com.example.qrcodegenerator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val qrButton: Button = findViewById(R.id.qrButton)
        val context: Context = this

        val onClickListener =
            View.OnClickListener {
                val qrTextView: TextView = findViewById(R.id.qrTextView)
                val qrImageView: ImageView = findViewById(R.id.qrImageView)
                qrImageView.setImageBitmap(
                    QRHandler.StringToQRCode(
                        context,
                        qrTextView.text.toString()
                    )
                )
            }

        qrButton.setOnClickListener(
            onClickListener
        )

    }

}