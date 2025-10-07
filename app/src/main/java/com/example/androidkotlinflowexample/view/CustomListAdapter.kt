package com.example.androidkotlinflowexample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidkotlinflowexample.R
import com.example.androidkotlinflowexample.data.CommentModel

class CustomListAdapter(private val dataList: List<CommentModel>) :
    RecyclerView.Adapter<CustomListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.textView.text = buildString {
            append(currentItem.id.toString())
            append(" - ")
            append(currentItem.postId.toString())
            append(" - ")
            append(currentItem.name)
            append(" - ")
            append(currentItem.email)
        }
    }

    override fun getItemCount() = dataList.size
}