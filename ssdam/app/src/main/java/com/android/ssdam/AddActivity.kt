package com.android.ssdam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.ssdam.sqLite.DiaryDB
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {

    var date = ""
    var color = ""
    var Ctite : EditText? = null
    var Ccontent: EditText? = null
    var addBtn: TextView? = null

    //sqlite
    var diaryDB: DiaryDB? = null

    var cTitle: String? = null
    var cContent: String? = null
    var cImageFileName: String? = null
    var cInsertDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        //SQLite
        diaryDB = DiaryDB(this)

        //값 받아오기------------------------------------------------
        val selectDay = intent.getStringExtra("selectDay")
        val selctColor = intent.getStringExtra("selectColor")
        //-----------------------------------------------------------
        if (selctColor != null) {
            color = selctColor
        }

        //yyyy년 MM월 dd일--------------------------------
         date = selectDay.toString()
        var diaryDate :TextView = findViewById(R.id.tv_Add_Date)
        diaryDate.text = "${date.subSequence(0,4)}년 ${date.subSequence(4,6)}월 ${date.subSequence(6,date.length)}일"

        Log.d("date","$date")

        image()

        // 제목, 내용
        Ctite =  findViewById(R.id.et_Add_Title)
        Ccontent = findViewById(R.id.et_Add_Content)


        addBtn = findViewById(R.id.tv_DiaryAdd)
        addBtn!!.setOnClickListener(onAddBtnClickListener)

    }

    fun image(){
        var imgColor :ImageView = findViewById(R.id.iv_Add_Image)

        when(color){
            "yellow" -> imgColor.setImageResource(R.drawable.yellow)
            "green" -> imgColor.setImageResource(R.drawable.green)
            "red" -> imgColor.setImageResource(R.drawable.red)
            "orange" -> imgColor.setImageResource(R.drawable.orange)
            "beige" -> imgColor.setImageResource(R.drawable.beige)
            "laidGray" -> imgColor.setImageResource(R.drawable.laidgray)
            "pink" ->  imgColor.setImageResource(R.drawable.pink)
            "purple" ->  imgColor.setImageResource(R.drawable.purple)
            "deepPurple" -> imgColor.setImageResource(R.drawable.deeppurple)
            "liteBlue" ->  imgColor.setImageResource(R.drawable.liteblue)
            "deepGray" -> imgColor.setImageResource(R.drawable.deepgray)
            "navy" ->  imgColor.setImageResource(R.drawable.navy)
        }

    }//image

    private var onAddBtnClickListener = View.OnClickListener {
        var db : SQLiteDatabase? = null

        cInsertDate = date
        cImageFileName = color
        cTitle = Ctite!!.text.toString().trim()
        cContent = Ccontent!!.text.toString().trim()

        Log.d("write","c $cTitle")
        Log.d("write","c $cImageFileName")

        if (cTitle != "" && cContent != ""){
            try {
                db = diaryDB!!.writableDatabase
                val query = "INSERT INTO contents (cTitle, cContent, cImageFileName, cInsertDate) VALUES ('$cTitle', '$cContent' , '$cImageFileName', '$cInsertDate');"
                db!!.execSQL(query)
                diaryDB!!.close()
                Toast.makeText(this, "$cInsertDate 일기작성 완료", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                print(e.printStackTrace())
                Toast.makeText(this, "Insert Error", Toast.LENGTH_LONG).show()
            }
            finish()
            startActivity(Intent(this, MainActivity::class.java))

            }else{
                Log.d("else", "c $cContent")
                var builder = AlertDialog.Builder(this)
                builder.setTitle("알림")
                builder.setMessage("내용을 작성해주세요")
                builder.setIcon(R.mipmap.ic_builder)
                builder.setPositiveButton("확인", null)

                builder.show()
            }
        }





}//====

