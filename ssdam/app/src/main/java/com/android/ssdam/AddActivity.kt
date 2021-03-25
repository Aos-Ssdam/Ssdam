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
    var color = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //값 받아오기------------------------------------------------
        val selectDay = intent.getStringExtra("selectDay")
        val selctColor = intent.getStringExtra("selectColor")
        //-----------------------------------------------------------
        if (selctColor != null) {
            color = selctColor
        }

        //yyyy년 MM월 dd일--------------------------------
         date = selectDay.toString()
        var diaryDate :TextView = findViewById(R.id.tv_Add_Date)
        diaryDate.text = "${date.subSequence(0,4)}년 ${date.subSequence(4,6)}월 ${date.subSequence(6,date.length)}일"

        image()

        // 작성버튼
        var AddBtn = findViewById<TextView>(R.id.tv_DiaryAdd)
        AddBtn.setOnClickListener {
            // 내용이 null이 아니면 추가
            startActivity(Intent(this,MainActivity::class.java))

            // 세미 추가
            var pref = getSharedPreferences("pref", 0)
            var pwOK = "OK"
            var edit = pref.edit()  // 수정모드
            edit.putString("pwOK", pwOK)    // 1번째 인자에는 키 값을, 2번쨰 인자에는 실제 담아둘 값
            edit.apply()    // 값을 저장 완료

            Toast.makeText(this,"저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()

            }
    }

    fun image(){
        var imgColor :ImageView = findViewById(R.id.iv_Add_Image)

        when(color){
            "yellow" -> imgColor.setImageResource(R.drawable.yellow)
            "green" -> imgColor.setImageResource(R.drawable.green)
            "red" -> imgColor.setImageResource(R.drawable.red)
            "orange" -> imgColor.setImageResource(R.drawable.orange)
            "beige" -> imgColor.setImageResource(R.drawable.beige)
            "laidGray" -> imgColor.setImageResource(R.drawable.laidgray)
            "pink" ->  imgColor.setImageResource(R.drawable.pink)
            "purple" ->  imgColor.setImageResource(R.drawable.purple)
            "deepPurple" -> imgColor.setImageResource(R.drawable.deeppurple)
            "liteBlue" ->  imgColor.setImageResource(R.drawable.liteblue)
            "deepGray" -> imgColor.setImageResource(R.drawable.deepgray)
            "navy" ->  imgColor.setImageResource(R.drawable.navy)
        }

    }//image

}//====

