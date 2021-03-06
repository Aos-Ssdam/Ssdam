package com.android.ssdam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.ssdam.Calendar.ColorDecorator
import com.android.ssdam.Calendar.MaxDecorator
import com.android.ssdam.Calendar.SaturdayDecorator
import com.android.ssdam.Calendar.SundayDecorator
import com.android.ssdam.sqLite.Diary
import com.android.ssdam.sqLite.DiaryDB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.format.TitleFormatter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    //뒤로가기 연속 클릭 대기 시간
    var mBackWait:Long = 0

    //버튼
    var isOpen = false

    //list에 넘길값
    var year  = ""
    var month  = ""

    // 일기 추가에 넘길 값
    var selectDay : String = ""

    val materialCalendar : MaterialCalendarView? = null


    //db select
    private var days: ArrayList<Diary>? = ArrayList()
    private var dirayDB: DiaryDB? = null
    private var calDay: ArrayList<CalendarDay> = ArrayList()
    private var calColor:ArrayList<Int> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //sqlite-----------------

        dirayDB = DiaryDB((this))
        materialCalendar?.setOnDateChangedListener { widget, date, selected ->

        }

        //-----------------------

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


       calendar()
        connectGetData()
        btn()

        Log.d("calday","$calDay")
        Log.d("color" , "$calColor")


    }//onCreate


    override fun onResume() {
        super.onResume()
       // connectGetData()
       calendar()
    }

    //보람 달력
    fun calendar() {

        val materialCalendar : MaterialCalendarView = findViewById(R.id.materialCalendar)

        var startTimeCalendar = Calendar.getInstance()
        var endTimeCalendar = Calendar.getInstance()

        val currentYear = startTimeCalendar.get(Calendar.YEAR)
        val currentMonth = startTimeCalendar.get(Calendar.MONTH)
        val currentDay = startTimeCalendar.get(Calendar.DATE)

        var monthStr = (currentMonth+1).toString()
        var dateStr = currentDay.toString()

        if (monthStr.toInt() < 10) {
            monthStr = "0$monthStr"
        }
        if (dateStr.toInt() < 10) {
            dateStr = "0$dateStr"
        }

      // 넘기는 초기값
        year = currentYear.toString()
        month = monthStr
        selectDay  =currentYear.toString()+ monthStr + dateStr


        // 달력 설정
        materialCalendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMaximumDate(CalendarDay.from(currentYear, currentMonth, 31))
                .commit()


        val enCalendarDay = CalendarDay(
                endTimeCalendar.get(Calendar.YEAR),
                endTimeCalendar.get(Calendar.MONTH),
                endTimeCalendar.get(Calendar.DATE)
        )

        materialCalendar.setSelectedDate(CalendarDay.today()) // 오늘 선택
        materialCalendar.setPadding(0, -20, 0, 30)


        //decorator
        val drawable = R.drawable.cal_beige

        val maxDecorator = MaxDecorator(enCalendarDay)
        val saturdayDecorator = SaturdayDecorator()
        val sundayDecorator = SundayDecorator()
        val colorDecorator = ColorDecorator( calDay, Color.parseColor("#97c1a0"))


        materialCalendar.addDecorators(
                saturdayDecorator,
                sundayDecorator,
                maxDecorator,
                colorDecorator
        )




        //선택한 날
        materialCalendar.setOnDateChangedListener{ widget, date, selected ->
            var year = date.year.toString()
            var month = (date.month+1).toString()
            var date  = date.day.toString()

            if (month.toInt() < 10) {
                month = "0$month"
            }
            if (date.toInt() < 10) {
                date = "0$date"
            }

            var selectDayMsg : String = year + "년" + month + "월" + date + "일"


            runOnUiThread{
                Toast.makeText(this, selectDayMsg, Toast.LENGTH_SHORT).show()
                selectDay = year + month + date
            }
        }

        // 처음 달력 title format
        materialCalendar.setTitleFormatter(TitleFormatter {
            val simpleDateFormat = SimpleDateFormat("yyyy년 MM월 ", Locale.KOREA)
            simpleDateFormat.format(startTimeCalendar.time)
        })

        //달력 넘길때 값 저장과 format
        materialCalendar.setOnMonthChangedListener { widget, date ->

            materialCalendar.setTitleFormatter(TitleFormatter {
                val simpleDateFormat = SimpleDateFormat("yyyy년 MM월 ", Locale.KOREA)
                simpleDateFormat.format(date.date)
            })
            year = (date.year).toString()
            var monthSt = (date.month+1).toString()

            if (monthSt.toInt() < 10) {
                month = "0$monthSt"
            }

        }


        // 리스트 이동
      materialCalendar.setOnTitleClickListener {

          val intent = Intent(this, ListActivity::class.java)
          // 값전달-----------------------------------
          intent.putExtra("year", year)
          intent.putExtra("month", month)
          //-----------------------------------------
          startActivity(intent)

      }

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
                    val intent = Intent(this, SelectColorActivity::class.java)
                    intent.putExtra("selectDay", selectDay)
                    Log.d("d", "selectDay $selectDay")
                    startActivity(intent)
                }

                settingBtn.setOnClickListener{
                    startActivity(Intent(this, SettingActivity::class.java))
                }
            }
        }

    }//btn


    private fun connectGetData(){

        var db: SQLiteDatabase? = null

        try{
            days!!.clear()
            db = dirayDB!!.readableDatabase
            val query = "Select  * From contents;"
            val cursor = db!!.rawQuery(query, null)
            while (cursor.moveToNext()) {
                val cImageFileName = cursor.getString(3)
                val cInsertDate= cursor.getString(4)
                val day = Diary(cInsertDate, cImageFileName)
                days!!.add(day)
                Log.d("cInsertDate","$cInsertDate $cImageFileName")
                var cYear = (cInsertDate.substring(0,4)).toInt()
                var cMonth = (cInsertDate.substring(4,6)).toInt()
                var cDay = (cInsertDate.substring(6,cInsertDate.length)).toInt()

                calDay.add(CalendarDay.from(cYear, (cMonth - 1), cDay))
                calColor.add(Color.parseColor("#ffffb5"))

            }

            cursor.close()
            dirayDB!!.close()
        }catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Select Error", Toast.LENGTH_SHORT).show()
        }

    }//connectGetData




    override fun onBackPressed() {
        //super.onBackPressed()
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
        } else {
            // 앱종료
            moveTaskToBack(true);

            finish();

            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }
}



