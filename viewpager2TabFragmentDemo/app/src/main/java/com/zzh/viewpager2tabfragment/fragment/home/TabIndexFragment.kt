package com.zzh.viewpager2tabfragment.fragment.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import com.zzh.viewpager2tabfragment.adapter.MainTabRecyclerviewAdapter
import com.zzh.viewpager2tabfragment.R
import com.zzh.viewpager2tabfragment.adapter.MyBannerAdapter
import kotlinx.android.synthetic.main.fragment_tab_index.*

/**
 * create by ZhongZihao on 2020/3/31
 *
 * 首页Tab 首页
 */
class TabIndexFragment : Fragment(R.layout.fragment_tab_index) {
    private lateinit var mainTabAdapter: MainTabRecyclerviewAdapter
    private val numList = (1..100).map { "第 $it 条" }
    private val bannerImages = listOf(R.mipmap.img1, R.mipmap.img2, R.mipmap.img3)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initRecyclerView()
    }

    private fun initBanner() {
        banner.setIndicatorGravity(IndicatorConfig.Direction.LEFT)
            .setIndicator(CircleIndicator(context))
            .adapter = MyBannerAdapter(bannerImages)
    }

    private fun initRecyclerView() {
        mainTabAdapter = MainTabRecyclerviewAdapter(context!!, numList)
        rv_main_tab.adapter = mainTabAdapter
        rv_main_tab.layoutManager = LinearLayoutManager(context)
    }

}