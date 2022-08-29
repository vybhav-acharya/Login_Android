package com.example.last

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class SignInActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 1

     var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mGoogleSignInClient!!.signOut()
        findViewById<View>(R.id.sign_in_button).setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //Your code here
                signIn()
            }})
    }

     private fun signIn() {
        Log.i(TAG, "Comes to signin")
        val intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }
     fun signOut()
    {
        mGoogleSignInClient!!.signOut();
    }
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i(TAG, "Comes to activity")
        if (requestCode == RC_SIGN_IN) {
            Log.i(TAG, "Comes to activity request code correct")
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)

                val intent = Intent(this@SignInActivity, UserProfile::class.java).apply {
                    putExtra("Account_name",account!!.displayName)
                    putExtra("Account_email",account!!.email)
                   putExtra("Account_photo",account!!.photoUrl.toString())

                }
                startActivity(intent)

            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.

                Log.e("TAG", "signInResult:failed code=" + e.statusCode)
                onStart();
            }
        }
    }
}
