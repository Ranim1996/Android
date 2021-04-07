package com.example.savinfloggingdatasharedpreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //shared prefernces string and mode
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        //save button event click: save data to shared preferences

        var btnLogIn = findViewById<Button>(R.id.login)
        var btnShowSavedData = findViewById<Button>(R.id.saveData)
        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.password)
        var checkbox = findViewById<CheckBox>(R.id.checkbox)
        var showData = findViewById<TextView>(R.id.savedData)

        btnLogIn.setOnClickListener{

            if (username.text.toString().trim().length < 1 || password.text.toString().trim().length < 1){
                Toast.makeText(this, "First fill your data" + " :)" ,
                    Toast.LENGTH_LONG).show();

            }
            else{

                val name = username.text.toString().trim()
//                val pass = password.text.toString().trim()
                val checked = checkbox.isChecked // checked true else false
                // edir shared preferences to save data
                val editor = sharedPreferences.edit()
                // save data in shared prefernce
                editor.putString("Username: ", name)
                editor.putBoolean("Checking whether you want save your data is: ", checked)

                // apply changes to shared prferences
                editor.apply()

                Toast.makeText(this, "You Signed in sucessfully \n" + "Welcome " + username.text + " :)" ,
                    Toast.LENGTH_LONG).show();
            }
        }

        // my identity button click event to show saved data by sharedprefernces
        btnShowSavedData.setOnClickListener{

            val username = sharedPreferences.getString("Username: ", "")
            val checked = sharedPreferences.getBoolean("Checking whether you want save your data is: ", false)

            if (checked == true){
                showData.text = "Your name is: " + username
            }
            else{
                showData.text = "Data not saved"
            }


        }

    }
}