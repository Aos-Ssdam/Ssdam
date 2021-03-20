    package com.android.ssdam

import android.content.Intent
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
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

class CheckEmailActivity : AppCompatActivity() {

    lateinit var authNum: String    // 문자로 온 인증번호

    private lateinit var auth: FirebaseAuth

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
        tv_pwcheckPhone.setText("+82" + savePhone + "로 \n인증번호를 발송합니다.")

        // 발송하기 버튼 눌렀을때
        btn_sendPhone.setOnClickListener {
            // 문자발송 구현
            et_CheckPhone.isVisible = true
            btn_check.isVisible = true

            auth = Firebase.auth
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+82" + savePhone)  // 전화번호
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
                        authNum = verificationId
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

            if (et_CheckPhone.text.toString().isEmpty()){
                Toast.makeText(this,"입력해주세요", Toast.LENGTH_SHORT).show()
            }else{
                verifyPhoneNumberWithCode(authNum, et_CheckPhone.text.toString())
                Toast.makeText(this,"비밀번호가 초기화 되었습니다.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,FindpwActivity::class.java))
                var pref = getSharedPreferences("pref", 0)
                var edit = pref.edit()  // 수정모드
                edit.remove("pw")    // 1번째 인자에는 키 값을, 2번쨰 인자에는 실제 담아둘 값
                edit.commit()    // 값을 저장 완료
            }

        }

    } //----------

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        // [START verify_with_code]
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("태그", "signInWithCredential:success")

                        val user = task.result?.user
                        // ...
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w("태그", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                        }
                    }
                }
    }

    private fun enableUserManuallyInputCode() {
        // No-op
    }

}