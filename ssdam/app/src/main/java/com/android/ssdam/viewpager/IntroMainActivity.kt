package com.android.ssdam.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.android.ssdam.R

class IntroMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val pager = findViewById<ViewPager2>(R.id.intro_ViewPager)

        // 뷰페이저2 설정
        val fragmentList = listOf(Fragment_Intro1(), Fragment_Intro2(), Fragment_Intro3())
        val adapter = ViewPagerAdapter(this)

        adapter.fragments = fragmentList
        pager.adapter = adapter // 뷰페이저2에 어댑터 연결

    }
}