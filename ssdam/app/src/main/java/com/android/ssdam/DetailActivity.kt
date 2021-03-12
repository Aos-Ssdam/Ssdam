package com.android.ssdam

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        delete()
        update()

    }

    //삭제버튼
    fun delete(){

        var deleteBtn = findViewById<TextView>(R.id.tv_Detail_Delete)

        deleteBtn.setOnClickListener {

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
                            startActivity(Intent(this@DetailActivity,ListActivity::class.java))
                    }
                }
            }

            deleteAlert.setPositiveButton("네" , yesClick)
            deleteAlert.setNegativeButton("아니요", null)
            deleteAlert.show()
        }
    }//delete


    //수정
    fun update(){

        var updateBtn = findViewById<TextView>(R.id.tv_Detail_Update)

        updateBtn.setOnClickListener {

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
        }
    }//update

}//===