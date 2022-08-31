package com.example.last

import android.R
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.last.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException


class SignInActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 1

    var mGoogleSignInClient: GoogleSignInClient? = null
    private lateinit var binding: ActivitySignInBinding
    private lateinit var countryCode: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ccp.onItemSelectedListener=object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i(TAG,"Came here ")
                countryCode = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                countryCode = "+91"
            }


        }

        binding.generateOtp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val number = binding.etPhone.text.toString()



                val phoneNumber = this@SignInActivity.getPhoneNumber(number);


                if (phoneNumber != null) {
                    val intent = Intent(this@SignInActivity, OtpActivity::class.java).apply {
                        putExtra("Phone_number", "$countryCode $phoneNumber")

                    }
                    startActivity(intent)


                }
            }

        })



        binding.signInButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //Your code here
//                signIn()
                val intent:Intent=Intent(this@SignInActivity,GoogleSignInn::class.java)
                startActivity(intent)
                finish()
            }
        })
    }







         fun getPhoneNumber(phone: String): String? {
            if (phone.isEmpty()) {
                Toast.makeText(applicationContext, "Phone number is required!", Toast.LENGTH_LONG)
                    .show()

            }

            else {
                return if (phone.substring(0, 1) == "0") phone.substring(1) else phone

            }
            return null
        }




}


