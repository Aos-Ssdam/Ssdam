package com.android.ssdam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.ssdam.Calendar.MaxDecorator
import com.android.ssdam.Calendar.SaturdayDecorator
import com.android.ssdam.Calendar.SundayDecorator
import com.android.ssdam.sqLite.DiaryDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import java.text.SimpleDateFormat
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
                startActivity(Intent(this, PasswordActivity::class.java))
            }
        }//----------------------------------

        //sqLite 불러오기
        diaryDB = DiaryDB(this, "newdb.db", null, 1)
        database = diaryDB.writableDatabase



        btn()

    }//onCreate


    override fun onResume() {
        super.onResume()
        calendar()
    }

    //보람 달력
    fun calendar() {

        val materialCalendar : MaterialCalendarView = findViewById(R.id.materialCalendar)

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
        val saturdayDacorator = SaturdayDecorator()
        val sundayDecorator = SundayDecorator()

//        materialCalendar.topbarVisible = false // 월 안보이기
        materialCalendar.setSelectedDate(CalendarDay.today()) // 오늘 선택
        materialCalendar.setPadding(0, -20, 0, 30)

        materialCalendar.state().edit()
            .setFirstDayOfWeek(Calendar.SUNDAY)
            .setMaximumDate(CalendarDay.from(currentYear, currentMonth, 31))
            .commit()

        materialCalendar.addDecorators(
            saturdayDacorator,
            sundayDecorator,
            maxDecorator
        )

        //선택한 날
        materialCalendar.setOnDateChangedListener{ widget, date, selected ->
            var year = date.year.toString()
            var month = (date.month+1).toString()
            var date  = date.day.toString()
            var selectDayMsg : String = year + "년" + month + "월" + date + "일"
            runOnUiThread{
                Toast.makeText(this, selectDayMsg, Toast.LENGTH_SHORT).show()
            }
        }

//        materialCalendar.setTitleFormatter(TitleFormatter {
//            val simpleDateFormat = SimpleDateFormat("yyyy년 MM월")
//            simpleDateFormat.format(startTimeCalendar.time) // 다 삼월만 나옴..
//        })





    }//calendar

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
                    startActivity(Intent(this, SelectColorActivity::class.java))
                }

                settingBtn.setOnClickListener{
                    startActivity(Intent(this, SettingActivity::class.java))
                }
            }
        }

    }//btn
}