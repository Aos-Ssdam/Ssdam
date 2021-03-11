package com.android.ssdam

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView


class SelectColorActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_color)

        val test = findViewById<ImageView>(R.id.iv_Yellow)
       test.setOnClickListener(object : View.OnClickListener{
           override fun onClick(v: View?) {
               when(v?.id){
                   
               }
           }
       })


    }
}



