package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class contactActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        supportActionBar?.hide()

        val btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val intent = Intent(this, addContact::class.java)
            startActivity(intent)
        }

    }
}