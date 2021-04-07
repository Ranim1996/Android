package com.example.simplesignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* when the button is clicked display a toast*/

        val button = findViewById<Button>(R.id.button)

        val userName = findViewById<EditText>(R.id.editTextTextPersonName2)
        val password = findViewById<EditText>(R.id.editTextTextPassword)

        button.setOnClickListener{
            Toast.makeText(this, "You Signed in sucessfully \n" + "Welcome " +userName.text + " :)" ,Toast.LENGTH_LONG).show();
        }
    }
}