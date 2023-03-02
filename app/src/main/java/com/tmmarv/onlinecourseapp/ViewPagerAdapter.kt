package com.tmmarv.onlinecourseapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val images: List<Int>, private val introOne: List<String>, private val introTwo: List<String>):RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    inner class ViewPagerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_intro)
        val intoO: TextView = itemView.findViewById(R.id.text_intro_one);
        val intoT: TextView = itemView.findViewById(R.id.text_intro_two);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.into_items, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currImage = images[position]
        val intoOn = introOne[position]
        val intoTw = introTwo[position]
        holder.imageView.setImageResource(currImage)
        holder.intoO.text = intoOn
        holder.intoT.text = intoTw
    }

    override fun getItemCount(): Int {
        return images.size
    }
}