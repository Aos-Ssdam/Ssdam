package com.android.firebasesmstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_PhoneNumber : EditText = findViewById(R.id.et_PhoneNumber)
        val btn_SendSMS : Button = findViewById(R.id.btn_SendSMS)

        btn_SendSMS.setOnClickListener(View.OnClickListener {
            val auth = Firebase.auth
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(et_PhoneNumber.text.toString())       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // Activity (for callback binding)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    override fun onCodeSent(
                        verificationId: String,
                        forceResendingToken: PhoneAuthProvider.ForceResendingToken
                    ) {
                        // Save the verification id somewhere
                        // ...
                        Log.d("verificationId", "인증번호 : " + verificationId)
                        // The corresponding whitelisted code above should be used to complete sign-in.
                        this@MainActivity.enableUserManuallyInputCode()
                    }

                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                        // Sign in with the credential
                        // ...
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        // ...
                    }
                })          // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        })


    }

    private fun enableUserManuallyInputCode() {
        // No-op
    }
}