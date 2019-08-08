package com.cxrus.cxchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnEnterChatRoom.setOnClickListener {
            if (etName.text.isNotEmpty()){

            }else{
                Toast.makeText(this,"Enter your name!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
