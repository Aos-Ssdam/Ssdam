package com.android.ssdam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.ssdam.sqLite.DiaryDB

class MainActivity : AppCompatActivity() {

    //sqLite
    lateinit var diaryDB  : DiaryDB
    lateinit var  database: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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