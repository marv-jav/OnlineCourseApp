package com.tmmarv.onlinecourseapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmmarv.onlinecourseapp.R
import com.tmmarv.onlinecourseapp.models.CourseModel

internal class CourseAdapter(private var itemsList: ArrayList<CourseModel>) : RecyclerView.Adapter<CourseAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var amountTv: TextView = view.findViewById(R.id.amount_tv)
        var titleTv: TextView = view.findViewById(R.id.course_title_tv)
        var subtitleTv: TextView = view.findViewById(R.id.course_subtitle_tv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.amountTv.text = "₦${item.amount}"
        holder.titleTv.text = item.title
        holder.subtitleTv.text = item.subtitle
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}