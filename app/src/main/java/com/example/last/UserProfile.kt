package com.example.last

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class UserProfile : AppCompatActivity() {

    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        val person_name: TextView = findViewById<View>(R.id.person_name) as TextView
        val person_email: TextView = findViewById<View>(R.id.person_email) as TextView
        val person_id: TextView = findViewById<View>(R.id.person_id) as TextView
        val signout_btn = findViewById<View>(R.id.signout_btn)
        val extras = intent.extras
        person_id.text=  extras!!.getString("Account_name")

        signout_btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //Your code here
                val intent= Intent(applicationContext, SignInActivity::class.java)
                startActivity(intent)


            }})




        Log.i(ContentValues.TAG, "Comes to user request ")

//            val personName = acct.displayName
//
//            person_name.text = personName



//            val personId = acct.id
//            person_id.text = personId
        }

    }





