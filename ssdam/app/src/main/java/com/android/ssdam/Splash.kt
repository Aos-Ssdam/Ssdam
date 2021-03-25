package com.android.ssdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.android.ssdam.viewpager.IntroMainActivity

class Splash : AppCompatActivity() {

    val SPLASH_VIEW_TIME: Long = 2000 // 2초간 스플래시 화면 노출

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            var pref = getSharedPreferences("hide", 0)
            var hideValue = pref.getBoolean("hideValue", false)

            if(hideValue != true){
                startActivity(Intent(this,IntroMainActivity::class.java))
            }else{
                startActivity(Intent(this,MainActivity::class.java))
            }
            finish()

        }, SPLASH_VIEW_TIME)
    }
}