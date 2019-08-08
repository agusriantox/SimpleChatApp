package com.cxrus.cxchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_chat.view.*

import java.util.ArrayList

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {
    private var messageList: ArrayList<Message> = ArrayList()

    fun addMessage(message : Message){
        messageList.add(message)
        notifyItemInserted(messageList.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(messageList[position])
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(message: Message) {
            itemView.tvName.text = message.nickname
            itemView.tvMessage.text = message.message
        }
    }
}
