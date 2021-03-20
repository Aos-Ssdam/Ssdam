package com.android.ssdam

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //값 받아오기-------------------------------------
        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        //--------------------------------------------


        // YYYY년 MM월
        var title: TextView = findViewById(R.id.list_title)
        title.text = "$year 년 $month 월"


    }
}