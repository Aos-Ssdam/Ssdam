package com.android.ssdam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class FindpwActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpw)

        val cb_password = findViewById<CheckBox>(R.id.cb_password)
        val et_password = findViewById<EditText>(R.id.et_password)
        val btn_pwOk = findViewById<Button>(R.id.btn_pwOk)


        // 실행시 저장된 비밀번호가 있으면 체크박스 체크
        checkPw()

        // 비밀번호 설정 시 edittext, button보이기
        cb_password.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                et_password.isVisible = true
                btn_pwOk.isVisible = true
            }else{
                et_password.isVisible = false
                btn_pwOk.isVisible = false
            }
        }

        // 입력된 비밀번호 SharedPreferences에 저장
        btn_pwOk.setOnClickListener {
            var setpw = et_password.text.toString()
            var pref = getSharedPreferences("pref", 0)
            var edit = pref.edit()  // 수정모드
            edit.putString("pw", setpw)    // 1번째 인자에는 키 값을, 2번쨰 인자에는 실제 담아둘 값
            edit.apply()    // 값을 저장 완료

            Toast.makeText(this, "비밀번호가 저장되었습니다." + pref.getString("pw", ""), Toast.LENGTH_SHORT).show()
            Log.d("태그","!!!!!!!!비밀번호 : " + setpw)

            val intent = Intent(this, SettingActivity::class.java)
           // startActivity(intent)
        }
    }

    // 비밀번호 저장이 되어있는지 체크, 체크박스
    fun checkPw(){
        var pref = getSharedPreferences("pref", 0)
        var savePw = pref.getString("pw", "")
        val cb_password = findViewById<CheckBox>(R.id.cb_password)
        Log.d("태그", "저장된 비밀번호 : " +savePw)

        if(savePw == ""){
            cb_password.isChecked = false
        }else{
            cb_password.isChecked = true
        }

    }

}