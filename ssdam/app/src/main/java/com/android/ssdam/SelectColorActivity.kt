package com.android.ssdam

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView


class SelectColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_color)

        imageViewClick()

        // 선택
        var selectBtn = findViewById<TextView>(R.id.tv_colorSelect)
        selectBtn.setOnClickListener {
            startActivity(Intent(this@SelectColorActivity,AddActivity::class.java)) }

    }

    fun imageViewClick(){  // 이미지뷰 clickListener

        val yellow = findViewById<ImageView>(R.id.iv_Yellow)
        val green = findViewById<ImageView>(R.id.iv_Green)
        val red = findViewById<ImageView>(R.id.iv_Red)
        val orange = findViewById<ImageView>(R.id.iv_Orange)
        val beige = findViewById<ImageView>(R.id.iv_Beige)
        val laidGray = findViewById<ImageView>(R.id.iv_Laidgray)
        val pink = findViewById<ImageView>(R.id.iv_Pink)
        val purple = findViewById<ImageView>(R.id.iv_Purple)
        val deepPurple = findViewById<ImageView>(R.id.iv_deepPurple)
        val liteBlue = findViewById<ImageView>(R.id.iv_Liteblue)
        val deepGray = findViewById<ImageView>(R.id.iv_DeepGray)
        val navy = findViewById<ImageView>(R.id.iv_Navy)

        yellow.setOnClickListener {
            yellow.isSelected = true
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }
        green.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = true
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }
        red.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = true
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }

        orange.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = true
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }
        beige.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = true
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }
        laidGray.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = true
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }

        pink.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = true
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }
        purple.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = true
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }
        deepPurple.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = true
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = false
        }

        liteBlue.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = true
            deepGray.isSelected = false
            navy.isSelected = false
        }
        deepGray.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = true
            navy.isSelected = false
        }
        navy.setOnClickListener {
            yellow.isSelected = false
            green.isSelected = false
            red.isSelected = false
            orange.isSelected = false
            beige.isSelected = false
            laidGray.isSelected = false
            pink.isSelected = false
            purple.isSelected = false
            deepPurple.isSelected = false
            liteBlue.isSelected = false
            deepGray.isSelected = false
            navy.isSelected = true
        }

    }//imageViewClick

}//====



