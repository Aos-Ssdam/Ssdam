package com.android.ssdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {    // 액티비티의 실행 시작 지점
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val item = arrayOf("비밀번호 설정", "공유하기", "백업하기", "개발자에게")
        // context란 한 액티비티의 모든 정보를 담고있다.
        val settingList = findViewById<ListView>(R.id.settingList)
        settingList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)

        settingList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position)
           if(selectItem.equals("비밀번호 설정")){
              // Toast.makeText(this, "비밀번호 설정", Toast.LENGTH_SHORT).show()
               val intent = Intent(this, FindpwActivity::class.java)
               startActivity(intent)

        }else if(selectItem.equals("공유하기")){
              // Toast.makeText(this, "공유하기", Toast.LENGTH_SHORT).show()
               var builder = AlertDialog.Builder(this)
               builder.setTitle("알림")
               builder.setMessage("준비중인 기능입니다.")
               builder.setIcon(R.mipmap.ic_launcher)
               builder.setPositiveButton("확인", null)

               builder.show()
           }else if(selectItem.equals("백업하기")){
              // Toast.makeText(this, "백업하기", Toast.LENGTH_SHORT).show()
               var builder = AlertDialog.Builder(this)
               builder.setTitle("알림")
               builder.setMessage("준비중인 기능입니다.")
               builder.setIcon(R.mipmap.ic_launcher)
               builder.setPositiveButton("확인", null)

               builder.show()
           }else if(selectItem.equals("개발자에게")){
              // Toast.makeText(this, "개발자에게", Toast.LENGTH_SHORT).show()
               var builder = AlertDialog.Builder(this)
               builder.setTitle("알림")
               builder.setMessage("민우이메일넣어야")
               builder.setIcon(R.mipmap.ic_launcher)
               builder.setPositiveButton("확인", null)

               builder.show()
           }
        }
    }
}