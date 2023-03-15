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

class loginActivity : AppCompatActivity() {
    private lateinit var databasereference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val loginButton = findViewById<Button>(R.id.btnLogin)
        val uniqueID = findViewById<TextInputEditText>(R.id.etUserName)

        loginButton.setOnClickListener {
            val userNameString =
                uniqueID.text.toString() // pehle humne username ki value find out karli
//            val passString = pass.text.toString()

            if ((userNameString).isNotEmpty()) {
                // agar empty ni hoga toh hum read karenge data from database
                //to read the data we will create a method
                readData(userNameString)

            } else {
                Toast.makeText(this, "Please Enter the User name", Toast.LENGTH_SHORT).show()
            }



//            if (passString.isNotEmpty()) {
//                readData(userNameString)
//            } else {
//                Toast.makeText(this, "Please Enter the password", Toast.LENGTH_SHORT).show()
//            }

        }

        val createText = findViewById<TextView>(R.id.tvCreate)
        createText.setOnClickListener {
            val openIntent = Intent(this, signUp::class.java)
            startActivity(openIntent)
        }
    } // on

    private fun readData(userNameString: String) {
        //uss node tak ka path chahiye
        databasereference = FirebaseDatabase.getInstance().getReference("users")
        //ab hume database ke childs dekhne hain
        //yahan pe set value ni karenge kyuki hume yahan values push ni karni .. hum yahan get karenge kyuki hume yahan value check karni h ki
//        exist karti h ya ni
        // agar mil jaata h toh add on success listner wala chalega ni toh add on failure chalega
            databasereference.child(userNameString).get().addOnSuccessListener {
                if (it.exists()) {
                    //take user to a new page , intent
                    // collect data from the database and show it to the user
                    // here it means ki hume username tak ka pata mil rha h ab hum unke childs ke saath khelenge
//                    val email = it.child("mail").value
//                    val name = it.child("name").value
//                    val usernamee = it.child("username").value

                    val intentWelcome = Intent(this, contactActivity::class.java)
                    startActivity(intentWelcome)
                }
                else {
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
                }
            }
            //here we will check if user exists or not
        }
    }

