package com.zzh.viewpager2tabfragment.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter


/**
 * create by ZhongZihao on 2020/4/3
 *
 */
class MyBannerAdapter(list: List<Int>) : BannerAdapter<Int, MyBannerAdapter.ImageHolder>(list) {

    inner class ImageHolder(itemView: ImageView) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ImageHolder {
        val imageView = ImageView(parent!!.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.layoutParams = params
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ImageHolder(imageView)
    }

    override fun onBindView(holder: ImageHolder?, data: Int, position: Int, size: Int) {
        holder?.imageView?.setImageResource(data)
    }
}