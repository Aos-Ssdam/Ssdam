package com.android.ssdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {

    var date = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //값 받아오기------------------------------------------------
        val selectDay = intent.getStringExtra("selectDay")
        val selctColor = intent.getStringExtra("selectColor")
        Log.d("d","여긴 add $selectDay")
        //-----------------------------------------------------------

        //yyyy년 MM월 dd일--------------------------------
         date = selectDay.toString()
        var diaryDate :TextView = findViewById(R.id.tv_Add_Date)
        diaryDate.text = "${date.subSequence(0,4)}년 ${date.subSequence(4,5)}월 ${date.subSequence(6,date.length)}일"

        //이미지
        var addImage: ImageView = findViewById(R.id.iv_Add_Image)
        addImage.setImageResource(R.drawable.orange)


        // 작성버튼
        var AddBtn = findViewById<TextView>(R.id.tv_DiaryAdd)
        AddBtn.setOnClickListener {
            // 내용이 null이 아니면 추가
            startActivity(Intent(this,MainActivity::class.java))

            }


    }

}//====

