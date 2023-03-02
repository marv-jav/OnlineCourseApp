package com.tmmarv.onlinecourseapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tmmarv.onlinecourseapp.R
import com.tmmarv.onlinecourseapp.models.CategoryModel

internal class CategoryAdapter(private var itemsList: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.category_text)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.category
    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}