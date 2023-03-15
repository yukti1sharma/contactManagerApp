package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signUp : AppCompatActivity() {

    lateinit var database : DatabaseReference

    companion object {
        const val KEY2 = "com.example.contactapp.signUp.name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()

        val signButton = findViewById<Button>(R.id.button)

        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etPass = findViewById<TextInputEditText>(R.id.etPassword)
        val etUser = findViewById<TextInputEditText>(R.id.etUser)

        signButton.setOnClickListener{
            val mail = etMail.text.toString()
            val name = etName.text.toString()
            val password = etPass.text.toString()
            val username = etUser.text.toString()

            val usern = user(mail, name, password, username)

            if(mail.isEmpty()){
                // agar empty ni hoga toh hum read karenge data from database
                //to read the data we will create a method
                Toast.makeText(this, "Please Enter the details", Toast.LENGTH_SHORT).show()
            }

            else{
                database = FirebaseDatabase.getInstance().getReference("users")
                database.child(username).setValue(usern).addOnSuccessListener {
                    Toast.makeText(this, "user registered successfully", Toast.LENGTH_SHORT).show()
                }

                val openIntent = Intent(this, loginActivity::class.java)
                startActivity(openIntent)
            }
        }

        val loginText = findViewById<TextView>(R.id.tvLogin)
        loginText.setOnClickListener{
            val openIntent = Intent(this, loginActivity::class.java)
            startActivity(openIntent)
        }
    }
}