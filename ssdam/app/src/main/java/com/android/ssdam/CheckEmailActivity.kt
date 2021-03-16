package com.android.ssdam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.regex.Matcher
import java.util.regex.Pattern

class CheckEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_email)

        val et_CheckEmail = findViewById<EditText>(R.id.et_checkEmail)
        val btn_check = findViewById<Button>(R.id.btn_check)

        var pref = getSharedPreferences("pref", 0)
        var saveEmail = pref.getString("email", "")

        // 확인 버튼 눌렀을때
        btn_check.setOnClickListener {

            var checkEmail = et_CheckEmail.text.toString()
            Log.d("태그", "이메일 확인 들어오니" + checkEmail)
            if (!isEmail(checkEmail)){

                var builder = AlertDialog.Builder(this)
                builder.setTitle("알림")
                builder.setMessage("잘못된 이메일 형식입니다.")
                builder.setIcon(R.mipmap.ic_launcher)
                builder.setPositiveButton("확인", null)

                builder.show()

            }else{
                if(checkEmail != saveEmail){
                    var builder = AlertDialog.Builder(this)
                    builder.setTitle("알림")
                    builder.setMessage("등록된 이메일과 다릅니다.")
                    builder.setIcon(R.mipmap.ic_launcher)
                    builder.setPositiveButton("확인", null)

                    builder.show()

                    et_CheckEmail.setText("")
                }else{
                    //이메일 인증 구현
                }
            }
        }
    } //----------

    // 이메일 형식 체크
    open fun isEmail(email: String?): Boolean {
        var returnValue = false
        val regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"
        val p: Pattern = Pattern.compile(regex)
        val m: Matcher = p.matcher(email)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue
    }
}