package com.android.ssdam

import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.ssdam.sqLite.DiaryDB
import java.lang.Exception

class DetailActivity : AppCompatActivity() {

    var date: String? = null
    var color: String? = null
    var Title: EditText? = null
    var Content: EditText? = null


    var diaryDB: DiaryDB? = null

    var deleteBtn: TextView? = null
    var updateBtn: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        diaryDB = DiaryDB(this)

        //값 받아오기------------------------------------------------
        val insertDate = intent.getStringExtra("insertDate")
        val imageFileName = intent.getStringExtra("imageFileName")
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        //-----------------------------------------------------------
        color = imageFileName




        //yyyy년 MM월 dd일--------------------------------
        date = insertDate.toString()
        var diaryDate :TextView = findViewById(R.id.tv_Detail_Date)
        diaryDate.text = "${date!!.subSequence(0,4)}년 ${date!!.subSequence(4,6)}월 ${date!!.subSequence(6,date!!.length)}일"

        image()

        //가져온 내용 setting
        Title = findViewById(R.id.et_Detail_Title)
        Content = findViewById(R.id.et_Detail_Content)

        Title!!.setText(title)
        Content!!.setText(content)


        deleteBtn = findViewById(R.id.tv_Detail_Delete)
        deleteBtn!!.setOnClickListener(deleteBtnOnClick)

        updateBtn = findViewById(R.id.tv_Detail_Update)
        updateBtn!!.setOnClickListener(updateBtnOnClick)

    }

    // 색
    fun image(){
        var imgColor : ImageView = findViewById(R.id.iv_Detail_Image)

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

    //삭제버튼

     private var deleteBtnOnClick = View.OnClickListener {
            var db: SQLiteDatabase? = null

            // AlertDialog에서 네, 아니요 선택
            var deleteAlert = AlertDialog.Builder(this)
            deleteAlert.setTitle("삭제")
            deleteAlert.setMessage("일기를 삭제할 건가요?")
            deleteAlert.setCancelable(false) // 화면 터치해도 AlertDialog 닫히지않음


            // 네 누를때
            var yesClick = object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which) {
                        DialogInterface.BUTTON_POSITIVE ->

                            try {
                                db = diaryDB!!.writableDatabase
                                val query = "DELETE FROM contents WHERE cInsertDate = '$date';"
                                db!!.execSQL(query)
                                diaryDB!!.close()

                            } catch (e: Exception) {
                                print(e.printStackTrace())
                            }
                    }

                    finish()
                    startActivity(Intent(this@DetailActivity,MainActivity::class.java))
                }
            }

            deleteAlert.setPositiveButton("네" , yesClick)
            deleteAlert.setNegativeButton("아니요", null)
            deleteAlert.show()
        }//delete



    //수정
    private var updateBtnOnClick = View.OnClickListener {

            // AlertDialog에서 네, 아니요 선택
            var updateAlert = AlertDialog.Builder(this)
            updateAlert.setTitle("수정")
            updateAlert.setMessage("일기를 수정할 건가요?")
            updateAlert.setCancelable(false) // 화면 터치해도 AlertDialog 닫히지않음

            // 네 누를때
            var yesClick = object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    when(which) {
                        DialogInterface.BUTTON_POSITIVE ->
                                startActivity(Intent(this@DetailActivity,ListActivity::class.java))
                    }
                }
            }

            updateAlert.setPositiveButton("네" ,yesClick)
            updateAlert.setNegativeButton("아니요", null)
            updateAlert.show()

    }//update

}//===


