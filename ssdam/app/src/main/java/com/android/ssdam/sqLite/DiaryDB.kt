package com.android.ssdam.sqLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DiaryDB(context: Context?) : SQLiteOpenHelper(context, "diary.db", null, 1){


    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE IF NOT EXISTS contents (cId INTEGER PRIMARY KEY AUTOINCREMENT,  cTitle TEXT, cContent TEXT, cImageFileName TEXT, cInsertDate TEXT, cUpdateDate TEXT);"
        db!!.execSQL(query)
    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS contents"
        db!!.execSQL(query)
        onCreate(db)
    }


}