package com.zzh.viewpager2tabfragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zzh.viewpager2tabfragment.R

/**
 * create by ZhongZihao on 2020/4/3
 */
class MainTabRecyclerviewAdapter(private val context: Context, private val data: List<String>) :
    RecyclerView.Adapter<MainTabRecyclerviewAdapter.MyViewHodler>() {

    inner class MyViewHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTextView: TextView = itemView.findViewById(R.id.tv_item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHodler {
        return MyViewHodler(LayoutInflater.from(context).inflate(R.layout.item_main_tab, parent,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHodler, position: Int) {
        holder.mTextView.text = data[position]
    }
}