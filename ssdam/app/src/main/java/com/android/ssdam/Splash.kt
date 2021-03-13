package com.android.ssdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

class Splash : AppCompatActivity() {

    val SPLASH_VIEW_TIME: Long = 2000 // 2초간 스플래시 화면 노출

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, SPLASH_VIEW_TIME)
    }
}