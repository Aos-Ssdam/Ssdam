package com.android.ssdam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible

class FindpwActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpw)

        val cb_password = findViewById<CheckBox>(R.id.cb_password)
        val et_password = findViewById<EditText>(R.id.et_password)

        cb_password.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                et_password.isVisible = true
            }else{
                et_password.isVisible = false
            }
        }

    }
}