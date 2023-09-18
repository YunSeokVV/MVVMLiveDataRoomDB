package com.example.mvvmlivedataroomdbpractice.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmlivedataroomdbpractice.R
import com.example.mvvmlivedataroomdbpractice.model.User


class UserAdapter(private val userData : List<User>, private val listener : OnItemClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    interface OnItemClickListener {
        fun onItemClick(user : User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userData[position])
    }

    override fun getItemCount(): Int {

        return userData.size
    }

    inner class ViewHolder(view  : View) : RecyclerView.ViewHolder(view) {
        val userName : TextView = itemView.findViewById(R.id.plantName)

        fun bind(item : User){
            userName.text = item.name
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(userData.get(adapterPosition))
            }
        }

    }
}