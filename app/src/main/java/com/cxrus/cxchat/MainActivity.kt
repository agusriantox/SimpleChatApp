package com.cxrus.cxchat

import android.content.Intent
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
                val i = Intent(this,ChatRoomActivity::class.java)
                i.putExtra(Constant.IntentKey.USERNAME, etName.text.toString())
                startActivity(i)
            }else{
                Toast.makeText(this,"Enter your name!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
