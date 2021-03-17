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
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

class CheckEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_email)

        val tv_pwcheckEmail = findViewById<TextView>(R.id.tv_pwcheckEmail)
        val et_CheckEmail = findViewById<EditText>(R.id.et_checkEmail)
        val btn_sendEmail = findViewById<Button>(R.id.btn_sendEmail)
        val btn_check = findViewById<Button>(R.id.btn_check)

        var pref = getSharedPreferences("pref", 0)
        var saveEmail = pref.getString("email", "")

        // 저장된 이메일로 발송 메세지
        tv_pwcheckEmail.setText(saveEmail + "로 \n 인증번호를 발송합니다.")

        // 발송하기 버튼 눌렀을때
        btn_sendEmail.setOnClickListener {
            // 전화번호 인증 발송
            et_CheckEmail.isVisible = true
            btn_check.isVisible = true


        }

        // 확인 버튼 눌렀을때 인증번호 맞는지 판단
        btn_check.setOnClickListener {

            // 이메일인증 구현

        }

    } //----------






}