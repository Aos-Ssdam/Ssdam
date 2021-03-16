package com.android.ssdam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import com.android.ssdam.Calendar.MaxDecorator
import com.android.ssdam.Calendar.SaturdayDacorator
import com.android.ssdam.Calendar.SundayDecorator
import com.android.ssdam.Calendar.TodayDecorator
import com.android.ssdam.sqLite.DiaryDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import java.util.*

class MainActivity : AppCompatActivity() {

    //sqLite
    lateinit var diaryDB  : DiaryDB
    lateinit var  database: SQLiteDatabase

    //버튼
    var isOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 세미 실행시 비밀번호 입력창 나오도록 구현
        var pref = getSharedPreferences("pref", 0)
        var savePw = pref.getString("pw", "")
        var pwOK = pref.getString("pwOK", "")
        var edit = pref.edit()  // 수정모드

        // 저장된 비밀번호가 있으면
        if(savePw != ""){
            Log.e("태그", "저장된 비밀번호 있음 : " + pref.getString("pwOK", ""))
            if (pwOK == "OK") {
                Log.e("태그", "pwOk완료 : " + pref.getString("pwOK", ""))
                edit.putString("pwOK", "fail")    // 1번째 인자에는 키 값을, 2번쨰 인자에는 실제 담아둘 값
                edit.apply()    // 값을 저장 완료
                Log.e("태그", "pwOK값 초기화 : " + pref.getString("pwOK", ""))
            }else{
                startActivity(Intent(this,PasswordActivity::class.java))
            }
        }//----------------------------------

        //sqLite 불러오기
        diaryDB = DiaryDB(this, "newdb.db",null,1)
        database = diaryDB.writableDatabase


        cal()
        btn()

    }//onCreate

    //보람 달력
    fun cal() {

        val materialCalendar = findViewById<MaterialCalendarView>(R.id.materialCalendar)
        materialCalendar.isSelected = true

        var startTimeCalendar = Calendar.getInstance()
        var endTimeCalendar = Calendar.getInstance()

        val currentYear = startTimeCalendar.get(Calendar.YEAR)
        val currentMonth = startTimeCalendar.get(Calendar.MONTH)
        val currentDate = startTimeCalendar.get(Calendar.DATE)

        //decorator
        val enCalendarDay = CalendarDay(
            endTimeCalendar.get(Calendar.YEAR),
            endTimeCalendar.get(Calendar.MONTH),
            endTimeCalendar.get(Calendar.DATE)
        )
        val maxDecorator = MaxDecorator(enCalendarDay)
        val todayDecorator = TodayDecorator(this)
        val saturdayDacorator = SaturdayDacorator()
        val sundayDecorator = SundayDecorator()

        materialCalendar.state().edit()
            .setFirstDayOfWeek(Calendar.SUNDAY)
            .setMaximumDate(CalendarDay.from(currentYear, currentMonth, 31))

            .commit()

        materialCalendar.addDecorators(
            saturdayDacorator,
            sundayDecorator,
            maxDecorator,
            todayDecorator
        )


    }//cal

    // 플로팅 버튼
    fun btn(){
        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close)
        val fabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)

        var mainBtn = findViewById<FloatingActionButton>(R.id.main_Btn)
        var addBtn = findViewById<FloatingActionButton>(R.id.main_Btn_Add)
        var settingBtn = findViewById<FloatingActionButton>(R.id.main_Btn_Setting)

        mainBtn.setOnClickListener {

            if (isOpen) {
                settingBtn.startAnimation(fabClose)
                addBtn.startAnimation(fabClose)
                mainBtn.startAnimation(fabRClockwise)

                isOpen = false
            } else {
                settingBtn.startAnimation(fabOpen)
                addBtn.startAnimation(fabOpen)
                mainBtn.startAnimation(fabRAntiClockwise)

                settingBtn.isClickable
                addBtn.isClickable

                isOpen = true

                addBtn.setOnClickListener {
                    startActivity(Intent(this,SelectColorActivity::class.java))
                }

                settingBtn.setOnClickListener{
                    startActivity(Intent(this,SettingActivity::class.java))
                }
            }
        }

    }//btn

}