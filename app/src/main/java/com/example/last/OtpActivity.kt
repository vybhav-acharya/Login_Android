package com.example.last


import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.last.databinding.ActivityOtpPart2Binding


class OtpActivity : AppCompatActivity() {
    companion object {
        const val DELAY: Long = 60000
        const val INTERVAL: Long = 1000
    }

    private lateinit var binding: ActivityOtpPart2Binding
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpPart2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val x: String? =intent.getStringExtra("Phone_number").toString()

editTextInput()

        binding.tvMobile.text = x
        binding.signInButton.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent:Intent=Intent(this@OtpActivity,GoogleSignInn::class.java)
                startActivity(intent)
            }
        })
        binding.btnVerify.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val c1 = binding.etC1.text.toString().trim()
                val c2 = binding.etC2.text.toString().trim()
                val c3 = binding.etC3.text.toString().trim()
                val c4 = binding.etC4.text.toString().trim()
                val smsCode = "$c1$c2$c3$c4"
                if(smsCode.length==4) {
                    val intent: Intent = Intent(this@OtpActivity, UserProfile::class.java)
                    startActivity(intent)
                    finish()
                }

            }
        })
        binding.editButton.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent:Intent=Intent(this@OtpActivity,SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
        binding.editer.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent:Intent=Intent(this@OtpActivity,SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
//        editTextInput()
        binding.toolbar.setNavigationOnClickListener {
            // back button pressed
            onBackPressed();
        }
        countDownTimer()

    }

    fun elapsedCountDownTimer(time: Long): String {
        val x:String=DateUtils.formatElapsedTime(time).replace(".", ":").toString()
        return "Resend OTP" + "(" + x.substring(x.length-2,x.length) + " s)"
    }

    private fun isVisible(visible: Boolean) {

        binding.btnVerify.isVisible = !visible
    }

    private fun countDownTimer() {
        countDownTimer = object : CountDownTimer(DELAY, INTERVAL) {
            override fun onTick(millisUtilsFinished: Long) {

                binding.textView7.text = elapsedCountDownTimer(millisUtilsFinished.div(INTERVAL))
                binding.tvResendBtn.isVisible = false
            }

            override fun onFinish() {
                binding.textView7.text = "Don't get the OTP?"
                binding.tvResendBtn.isVisible = true

                binding.tvResendBtn.setOnClickListener {
                    val intent: Intent = Intent(this@OtpActivity, SignInActivity::class.java)
                    startActivity(intent)
                }
            }
        }
        countDownTimer.start()
//

    }

    private fun editTextInput() {
        binding.etC1.addTextChangedListener(object : TextWatcher {

            private var prevLength = true
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


            override fun onTextChanged(charSequence: CharSequence, start: Int, end: Int, count: Int){
            }

            override fun afterTextChanged(editable: Editable) {
                if(prevLength==true)
                    binding.etC2.requestFocus()
                prevLength=!prevLength
                }

        })

        binding.etC2.addTextChangedListener(object : TextWatcher {

            private var prevLength = false

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                end: Int,
                count: Int
            ) {

            }

            override fun afterTextChanged(editable: Editable) {
                if(prevLength==true)
                    binding.etC1.requestFocus()
                else
                    binding.etC3.requestFocus()
                prevLength=!prevLength
            }

        })

        binding.etC3.addTextChangedListener(object : TextWatcher {

            private var prevLength = false

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                end: Int,
                count: Int
            ) {


            }

            override fun afterTextChanged(editable: Editable) {
                if(prevLength==true)
                    binding.etC2.requestFocus()
                else
                    binding.etC4.requestFocus()
                prevLength=!prevLength
            }

        })
        binding.etC4.addTextChangedListener(object : TextWatcher {

            private var prevLength = false

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                end: Int,
                count: Int
            ) {


            }

            override fun afterTextChanged(editable: Editable) {
                if(prevLength==true)
                    binding.etC3.requestFocus()


                prevLength=!prevLength

            }

        })
    }
}