package com.android.ssdam

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class PasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        var checkPw = findViewById<EditText>(R.id.et_CheckPw)
        var btn_checkPw = findViewById<Button>(R.id.btn_pwCheck)
        var pref = getSharedPreferences("pref", 0)
        var savePw = pref.getString("pw", "")


        // 확인 버튼을 클릭 시
        btn_checkPw.setOnClickListener {
            if (checkPw.text.toString() == savePw){
                var pwOK = "OK"
                var edit = pref.edit()  // 수정모드
                edit.putString("pwOK", pwOK)    // 1번째 인자에는 키 값을, 2번쨰 인자에는 실제 담아둘 값
                edit.apply()    // 값을 저장 완료

                startActivity(Intent(this,MainActivity::class.java))

                Log.d("태그", "비밀번호확인창 : " + savePw + "과" + checkPw.text.toString())
            }else{
                var builder = AlertDialog.Builder(this)
                builder.setTitle("경고!")
                builder.setMessage("비밀번호가 틀렸습니다.")
                builder.setIcon(R.mipmap.ic_launcher)
                builder.setPositiveButton("확인", null)

                builder.show()
            }
        }
    }
}