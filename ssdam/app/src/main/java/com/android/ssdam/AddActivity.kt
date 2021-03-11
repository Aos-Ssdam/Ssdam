package com.android.ssdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // 작성버튼
        var AddBtn = findViewById<TextView>(R.id.tv_DiaryAdd)
        AddBtn.setOnClickListener {
            // 내용이 null이 아니면 추가
            startActivity(Intent(this,MainActivity::class.java)) }
    }

}//====