    package com.android.ssdam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

class CheckEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_email)

        val tv_pwcheckPhone = findViewById<TextView>(R.id.tv_pwcheckPhone)
        val et_CheckPhone = findViewById<EditText>(R.id.et_checkPhone)
        val btn_sendPhone = findViewById<Button>(R.id.btn_sendPhone)
        val btn_check = findViewById<Button>(R.id.btn_check)

        var pref = getSharedPreferences("pref", 0)
        var savePhone = pref.getString("phone", "")

        // 저장된 번호에 문자 발송 메세지
        tv_pwcheckPhone.setText(savePhone + "로 \n인증번호를 발송합니다.")

        // 발송하기 버튼 눌렀을때
        btn_sendPhone.setOnClickListener {
            // 문자발송 구현
            et_CheckPhone.isVisible = true
            btn_check.isVisible = true

            val auth = Firebase.auth
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(savePhone)  // 전화번호
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                    override fun onCodeSent(
                        verificationId: String,
                        forceResendingToken: PhoneAuthProvider.ForceResendingToken
                    ) {
                        // Save the verification id somewhere
                        // ...
                        Log.d("verificationId", "인증번호 : " + verificationId)
                        // The corresponding whitelisted code above should be used to complete sign-in.
                        this@CheckEmailActivity.enableUserManuallyInputCode()
                    }

                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                        // Sign in with the credential
                        // ...
                    }

                    override fun onVerificationFailed(e: FirebaseException) {
                        // ...
                    }

                })
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)


        }

        // 확인 버튼 눌렀을때 인증번호 맞는지 판단
        btn_check.setOnClickListener {

            // 문자발송 구현



        }

    } //----------




    private fun enableUserManuallyInputCode() {
        // No-op
    }

}