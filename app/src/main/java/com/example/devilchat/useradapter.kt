package com.example.devilchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class userAdapter(val context:Context,val userlist:ArrayList<user>) :
    RecyclerView.Adapter<userAdapter.userViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
      val view:View=LayoutInflater.from(context).inflate(R.layout.userlayout,parent,false)
        return userViewHolder(view)
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val currentuser=userlist[position]
        holder.textName.text=currentuser.name

    }

    override fun getItemCount(): Int {
        return userlist.size
    }
    class userViewHolder( itemView : View ):RecyclerView.ViewHolder(itemView){
        val textName=itemView.findViewById<TextView>(R.id.nam)

    }
}