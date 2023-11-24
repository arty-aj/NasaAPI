package com.example.apiskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiskotlin.R
import com.example.apiskotlin.model.ApodResponse

class ApodAdapter (private var mList: ApodResponse?): RecyclerView.Adapter<ApodAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title: TextView = itemView.findViewById(R.id.tvTitle)
        var date: TextView = itemView.findViewById(R.id.tvDate)
        var explanation: TextView = itemView.findViewById(R.id.tvExplanation)
        var image: TextView = itemView.findViewById(R.id.tvImage)
        var copyright: TextView = itemView.findViewById(R.id.tvCopyright)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_apod_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mList != null) 1 else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mList?.let {
            holder.title.text = it.title
            holder.date.text = it.date
            holder.explanation.text = it.explanation
            holder.image.text = it.hdurl
            holder.copyright.text = it.copyright
        }
    }
}