package com.cxrus.cxchat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_chat_room.*
import org.json.JSONObject
import java.net.URISyntaxException


class ChatRoomActivity : AppCompatActivity() {

    private lateinit var socket : Socket
    private lateinit var adapter: ChatAdapter
    var name : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        adapter = ChatAdapter()
        rvChatList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvChatList.adapter = adapter

        try {
            socket = IO.socket("http://172.20.103.131:3000")
            socket.connect()
            name = intent.getStringExtra(Constant.IntentKey.USERNAME)
            socket.emit("join",name)
            Log.e("nama",name)

            socket.on("masuk") { args ->
                runOnUiThread {
                    val data = args[0] as String
                    Log.e("user-joined-chat", "data =  $data")
                    Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
                }
            }

            socket.on("keluar") {
                runOnUiThread {
                    val data:String = it[0] as String
                    Log.e("user-disconnect", "data =  $data")
                    Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
                }
            }

            socket.on("message") {
                runOnUiThread {
                    try {
                        val data: JSONObject = it[0] as JSONObject
                        val message : Message = Gson().fromJson(data.toString(), Message::class.java)
                        adapter.addMessage(message)

                        Log.e("message", "data =  $data")
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }

            btnSend.setOnClickListener {
                if(etMessage.text.toString().isNotEmpty()){
                    socket.emit("kirim",name, etMessage.text.toString())
                    etMessage.setText("")
                }
            }

        } catch (e : URISyntaxException) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.disconnect()
    }
}