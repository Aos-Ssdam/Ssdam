package com.android.ssdam

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog


class SelectColorActivity : AppCompatActivity() {


    var selectColor = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_color)

        //값 받아오기----------------------------------
        val selectDay = intent.getStringExtra("selectDay")
        Log.d("d","여긴 색선택이다 $selectDay")
        //--------------------------------------------


        imageViewClick()

        // 선택
        var selectBtn = findViewById<TextView>(R.id.tv_colorSelect)
        selectBtn.setOnClickListener {

            if (selectColor == "") {
                var builder = AlertDialog.Builder(this)
                builder.setTitle("알림")
                builder.setMessage("색을 선택해주세요.")
                builder.setIcon(R.mipmap.ic_builder)
                builder.setPositiveButton("확인", null)

                builder.show()
            } else {
                //값 전달-----------------------------------------------------------------
                val intent = Intent(this, AddActivity::class.java)
                intent.putExtra("selectDay", selectDay)
                intent.putExtra("selectColor", selectColor)
                Log.d("d", "색선택이다 $selectColor")
                startActivity(intent)
                //-----------------------------------------------------------------------
            }
        }//selectBtn

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

            selectColor = "yellow"
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

            selectColor = "green"

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

            selectColor = "red"
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

            selectColor = "orange"
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

            selectColor = "beige"
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

            selectColor = "laidGray"
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

            selectColor = "pink"
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

            selectColor = "purple"
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

            selectColor = "deepPurple"
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

            selectColor = "liteBlue"
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

            selectColor = "deepGray"
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

            selectColor = "navy"
        }

    }//imageViewClick

}//====



