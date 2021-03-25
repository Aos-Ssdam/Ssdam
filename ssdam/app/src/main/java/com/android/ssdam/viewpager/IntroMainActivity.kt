package com.android.ssdam.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.android.ssdam.R
import org.w3c.dom.Text

class IntroMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val pager = findViewById<ViewPager2>(R.id.intro_ViewPager)

        // 뷰페이저2 설정
        val fragmentList = listOf(Fragment_Intro1(), Fragment_Intro2(), Fragment_Intro3())
        val adapter = ViewPagerAdapter(this)
        val tv_Next = findViewById<TextView>(R.id.intro_tv_Next)
        val tv_Previous = findViewById<TextView>(R.id.intro_tv_Previous)

        tv_Previous.visibility = View.GONE


        adapter.fragments = fragmentList
        pager.adapter = adapter // 뷰페이저2에 어댑터 연결

        tv_Next.setOnClickListener {
            var current = pager.currentItem
            println("Current : ${current}")
            tv_Previous.visibility = View.VISIBLE
            pager.setCurrentItem(current + 1, true)

            if(current == 1){
                tv_Next.visibility = View.GONE
            }
        }

        tv_Previous.setOnClickListener {
            var current = pager.currentItem
            println("Current : ${current}")
            tv_Next.visibility = View.VISIBLE

            pager.setCurrentItem(current -1, true)

            if(current == 1){
                tv_Previous.visibility = View.GONE

            }
        }


    }
}