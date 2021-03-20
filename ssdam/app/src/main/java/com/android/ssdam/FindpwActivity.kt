package com.android.ssdam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Matcher
import java.util.regex.Pattern

class FindpwActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpw)

        val cb_password = findViewById<CheckBox>(R.id.cb_password)
        val et_password = findViewById<EditText>(R.id.et_password)
        val et_phone = findViewById<EditText>(R.id.et_phone)
        val btn_phoneOk = findViewById<Button>(R.id.btn_phoneOk)


        // 실행시 저장된 비밀번호가 있으면 체크박스 체크
        checkPw()

        // 비밀번호 설정 시 edittext, button 활성화
        cb_password.setOnCheckedChangeListener { buttonView, isChecked ->
            if(cb_password.isChecked == true){
                et_password.isEnabled = true
                et_phone.isEnabled = true
                btn_phoneOk.isEnabled = true
            }else if(cb_password.isChecked == false){
                et_password.isEnabled = false
                et_phone.isEnabled = false
                btn_phoneOk.isEnabled = false

                // 비밀번호 등록 되어 있던 SharedPreferences 초기화
                var pref = getSharedPreferences("pref", 0)
                var edit = pref.edit()  // 수정모드
                edit.remove("pw")    // 1번째 인자에는 키 값을, 2번쨰 인자에는 실제 담아둘 값
                edit.commit()    // 값을 저장 완료
                Toast.makeText(this, "비밀번호 설정 해제되었습니다." + pref.getString("pw", ""), Toast.LENGTH_SHORT).show()
            }
        }

        // 저장 버튼 클릭, 입력된 비밀번호, 이메일 SharedPreferences에 저장
        btn_phoneOk.setOnClickListener {

            var setpw = et_password.text.toString()
            var setphone = et_phone.text.toString()

            if (setpw.length != 6){
                var builder = AlertDialog.Builder(this)
                builder.setTitle("알림")
                builder.setMessage("비밀번호 6자리를 입력해 주세요.")
                builder.setIcon(R.mipmap.ic_launcher)
                builder.setPositiveButton("확인", null)

                builder.show()

            }else{

                if(setphone.length != 11){
                    var builder = AlertDialog.Builder(this)
                    builder.setTitle("알림")
                    builder.setMessage("전화번호를 정확하게 입력해주세요.")
                    builder.setIcon(R.mipmap.ic_launcher)
                    builder.setPositiveButton("확인", null)

                    builder.show()
                }else{
                    var pref = getSharedPreferences("pref", 0)
                    var edit = pref.edit()  // 수정모드
                    edit.putString("pw", setpw)    // 1번째 인자에는 키 값을, 2번쨰 인자에는 실제 담아둘 값
                    edit.putString("phone", setphone)
                    edit.apply()    // 값을 저장 완료

                    Toast.makeText(this, "정보가 저장되었습니다.", Toast.LENGTH_SHORT).show()
                    Log.d("태그","설정 페이지 비밀번호 & 이메일 : " + pref.getString("pw","") + " & " + pref.getString("phone", ""))

                    val intent = Intent(this, SettingActivity::class.java)
                     startActivity(intent)
                }
            }
        }
    }

    // 비밀번호, 전화번호 저장 되어있는지 체크, 체크박스
    fun checkPw(){
        var pref = getSharedPreferences("pref", 0)
        var savePw = pref.getString("pw", "")

        val cb_password = findViewById<CheckBox>(R.id.cb_password)
        val et_password = findViewById<EditText>(R.id.et_password)
        val et_phone = findViewById<EditText>(R.id.et_phone)
        val btn_pwOk = findViewById<Button>(R.id.btn_phoneOk)

        Log.d("태그", "저장된 비밀번호 : " +savePw)

        if(savePw == ""){
            cb_password.isChecked = false
            et_password.isEnabled = false
            et_phone.isEnabled = false
            btn_pwOk.isEnabled = false
        }else{
            cb_password.isChecked = true
            et_password.isEnabled = true
            et_phone.isEnabled = true
            btn_pwOk.isEnabled = true
        }

    }

}