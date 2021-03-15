package com.android.ssdam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.ssdam.sqLite.DiaryDB

class MainActivity : AppCompatActivity() {

    //sqLite
    lateinit var diaryDB  : DiaryDB
    lateinit var  database: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 세미 실행시 비밀번호 입력창 나오도록 구현
        var pref = getSharedPreferences("pref", 0)
        var savePw = pref.getString("pw", "")
        var pwOK = pref.getString("pwOK", "")
        var edit = pref.edit()  // 수정모드

        // 저장된 비밀번호가 있으면
        if(savePw != ""){
            Log.e("태그", "저장된 비밀번호 있음 : " + pref.getString("pwOK", ""))
            if (pwOK == "OK") {
                Log.e("태그", "pwOk완료 : " + pref.getString("pwOK", ""))
                edit.putString("pwOK", "fail")    // 1번째 인자에는 키 값을, 2번쨰 인자에는 실제 담아둘 값
                edit.apply()    // 값을 저장 완료
                Log.e("태그", "pwOK값 초기화 : " + pref.getString("pwOK", ""))
            }else{
                startActivity(Intent(this,PasswordActivity::class.java))
            }
        }//----------------------------------

        //sqLite 불러오기
        diaryDB = DiaryDB(this, "newdb.db",null,1)
        database = diaryDB.writableDatabase

        // 나중에 지울꺼... 지금은 이동이 필요해서정

        // 설정
        var moveSetting = findViewById<TextView>(R.id.btn_Movesetting)
        moveSetting.setOnClickListener {
            // 내용이 null이 아니면 추가
            startActivity(Intent(this,SettingActivity::class.java))
        }

        // 색선택
        var moveSelect = findViewById<TextView>(R.id.btn_MoveselectColor)
        moveSelect.setOnClickListener {
            // 내용이 null이 아니면 추가
            startActivity(Intent(this,SelectColorActivity::class.java))
        }

        // 디테일
        var moveDetail = findViewById<TextView>(R.id.btn_MoveDetail)
        moveDetail.setOnClickListener {
            // 내용이 null이 아니면 추가
            startActivity(Intent(this,DetailActivity::class.java))
        }

    }//onCreate

}