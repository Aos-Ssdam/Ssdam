package com.android.ssdam.sqLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DiaryDB(context: Context, name: String?,
              factory: SQLiteDatabase.CursorFactory?,
              version: Int) : SQLiteOpenHelper(context,name, factory, version) {


    override fun onCreate(db: SQLiteDatabase) {
        val sql :  String = "CREATE TABLE IF NOT EXISTS contents (cId INTEGER PRIMARY KEY AUTOINCREMENT," +
                " cTitle TEXT, cContent TEXT, cImageFileName TEXT, cInsertDate TEXT, cUpdateDate TEXT);"

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
       val sql :  String = "DROP TABLE if exists contents"

        db.execSQL(sql)
        onCreate(db)
    }


}