package com.example.last

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class OtpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_part2)
        val extras = intent.extras
        val p=findViewById<View>(R.id.tvMobile) as TextView
        p.text=extras!!.getString("Phone_number")

    }
}