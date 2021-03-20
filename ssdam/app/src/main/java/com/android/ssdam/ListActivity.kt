package com.android.ssdam

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.ssdam.sqLite.Diary
import com.android.ssdam.sqLite.DiaryDB
import com.android.ssdam.sqLite.diraryAdapter
import java.util.ArrayList

class ListActivity : AppCompatActivity() {

    private var members: ArrayList<Diary>? = ArrayList()
    private var adapter: diraryAdapter? = null
    private var dirayDB: DiaryDB? = null
    private var lvDiary: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        //sqlite-----------------

        dirayDB = DiaryDB((this))

        //-----------------------

        //값 받아오기-------------------------------------
        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        //--------------------------------------------


        // YYYY년 MM월
        var title: TextView = findViewById(R.id.list_title)
        title.text = "$year 년 $month 월"


    }

    private fun connectGetData(){

        var db: SQLiteDatabase? = null

        try{
            members!!.clear()
            db = dirayDB!!.readableDatabase
            val query = "Select cTitle, cInsertDate, cImageFileName, cContent From contents order by cInsertDate desc;"
            val cursor = db!!.rawQuery(query,null)
            while (cursor.moveToNext()) {
                val cTitle = cursor.getString(0)
                val cInsertDate = cursor.getString(1)
                val cImageFileName = cursor.getString(2)
                val cContent = cursor.getString(3)
                val member = Diary(cTitle,cInsertDate,cImageFileName,cContent)
                members!!.add(member)

            }
            cursor.close()
            dirayDB!!.close()
        }catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Select Error", Toast.LENGTH_SHORT).show()
        }

        try {
            lvDiary = findViewById(R.id.diaryList)
            adapter = diraryAdapter(this, R.layout.custom_dairylist_layout, members)
            lvDiary!!.adapter = adapter
            lvDiary!!.onItemClickListener = onItemClickListener

        }catch (e: Exception) {
            e.printStackTrace()
        }

    }//connectGetData

    override fun onResume() {
        super.onResume()
        connectGetData()
    }

    private var onItemClickListener: AdapterView.OnItemClickListener = object : AdapterView.OnItemClickListener {
        var intent: Intent? = null

        override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {

            intent = Intent(this@ListActivity,DetailActivity::class.java)
            intent!!.putExtra("insertDate", members!![position].insertDate)
            intent!!.putExtra("imageFileName", members!![position].imageFileName)
            intent!!.putExtra("title", members!![position].title)
            intent!!.putExtra("content", members!![position].content)
            startActivity(intent)
        }
    }



}//=====