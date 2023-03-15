package com.example.contactapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.sign

class addContact : AppCompatActivity() {

    lateinit var databaseContact : DatabaseReference
    lateinit var dialog : Dialog

    companion object{
        const val KEY1 = "com.example.contactApp.addContact.mail"
        const val KEY2 = "com.example.contactApp.addContact.phone"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        val addContact = findViewById<Button>(R.id.btnFinal)
        val enterEmail = findViewById<TextInputEditText>(R.id.etname)
        val phoneNum = findViewById<TextInputEditText>(R.id.etPhone)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)

        addContact.setOnClickListener {
            val mail = enterEmail.text.toString()
            val phone = phoneNum.text.toString()

            val contactclass = contact(mail, phone)

            if (phone.isNotEmpty()) {
                readDataa(phone)
            } else {
                Toast.makeText(this, "Please Enter the User name", Toast.LENGTH_SHORT).show()
            }

        }
        }

    fun readDataa(phone: String) {

        dialog = Dialog(this)
        dialog.setContentView(R.layout.alertbox)

        val btnOk = dialog.findViewById<Button>(R.id.btnOk)
        val btnAdd = findViewById<Button>(R.id.btnFinal)

        //uss node tak ka path chahiye
        databaseContact = FirebaseDatabase.getInstance().getReference("contacts")
        databaseContact.child(phone).get().addOnSuccessListener {
            if (it.exists()) {
                Toast.makeText(this, "Phone number already exists", Toast.LENGTH_SHORT).show()
            }
            else {
                val enterEmail = findViewById<TextInputEditText>(R.id.etname)
                val phoneNum = findViewById<TextInputEditText>(R.id.etPhone)
                val mail = enterEmail.text.toString()
                val phone1 = phoneNum.text.toString()
                val contactclass1 = contact(mail, phone1)
                databaseContact= FirebaseDatabase.getInstance().getReference("contacts")
                databaseContact.child(phone1).setValue(contactclass1).addOnSuccessListener {
//                    Toast.makeText(this, "Number registered successfully", Toast.LENGTH_SHORT).show()

                    btnAdd.setOnClickListener {
                        dialog.show()
                    }

                    btnOk.setOnClickListener {
                        dialog.dismiss()
                    }



                }

//
            }
        }
        //here we will check if user exists or not
    }


    }
