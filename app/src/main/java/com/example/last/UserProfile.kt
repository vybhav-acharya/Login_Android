package com.example.last

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import java.net.URL
import java.security.AccessController.getContext

class UserProfile : AppCompatActivity() {

    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val person_id: TextView = findViewById<View>(R.id.person_name) as TextView
        val person_email: TextView = findViewById<View>(R.id.person_email) as TextView
        val imageView=ImageView(this)

        val signout_btn = findViewById<View>(R.id.signout_btn)
        if(intent.extras!=null) {
            val extras = intent.extras
            person_id.text = extras!!.getString("Account_name")
            person_email.text = extras!!.getString("Account_email")


            Glide.with(applicationContext)
                .load(extras!!.getString("Account_photo"))
                .into(imageView);
        }
        signout_btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //Your code here
                val intent:Intent=Intent(this@UserProfile,SignInActivity::class.java)
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





