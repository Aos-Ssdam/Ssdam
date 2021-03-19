package com.android.ssdam

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ListActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        //값 받아오기

        var month: TextView = findViewById(R.id.list_title)

      month.setText(intent.getStringExtra("date"))


    }
}