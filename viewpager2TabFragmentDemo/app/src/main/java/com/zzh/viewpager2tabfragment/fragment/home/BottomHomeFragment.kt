package com.zzh.viewpager2tabfragment.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.zzh.viewpager2tabfragment.R
import com.zzh.viewpager2tabfragment.activity.MainActivity
import kotlinx.android.synthetic.main.tab_pager_layout.*

/**
 * create by ZhongZihao on 2020/3/31
 *
 * 底部菜单 首页 fragment
 */
class BottomHomeFragment : Fragment(R.layout.fragment_bottom_home) {
    private val tabTitleList = arrayOf("首页", "收租记录", "统计")
    private val tabFragmentList = arrayListOf<Fragment>()
    private val homeFragment by lazy { TabIndexFragment() } // 首页
    private val paymentListFragment by lazy { TabPaymentListFragment() } // 收租记录
    private val statisticsFragment by lazy { TabStatisticsFragment() } // 统计

    private lateinit var mainViewPager: ViewPager2 // 父ViewPager


    init {
        tabFragmentList.run {
            add(homeFragment)
            add(paymentListFragment)
            add(statisticsFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }


    private fun initViewPager() {
        mainViewPager = (activity as MainActivity).getMainViewPager()
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = tabFragmentList[position]

            override fun getItemCount() = tabTitleList.size
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleList[position]
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            private var currentPosition = 0     //当前滑动位置
            private var oldPositon = 0          //上一个滑动位置

            // 滚动状态改变回调，state的值分别有0，1，2 ;
            // 0为ViewPager所有事件(1,2)已结束触发
            // 1为在viewPager里按下并滑动触发多次
            // 2是手指抬起触发
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("TAG1","onPageScrollStateChanged $state")

                if (state == 0) {
                    if (currentPosition == oldPositon) {
                        when (currentPosition) {
                            0 -> {
                                Log.d("TAG1", "到达最左一个继续往左滑....")
                                //若还有上一个bottom fragment页面则切换
                                mainViewPager.currentItem.takeIf { it > 0 }
                                    ?.also { (activity as MainActivity).switchFragment(it - 1) }
                            }
                            (viewPager.adapter as FragmentStateAdapter).itemCount - 1 -> {
                                Log.d("TAG1", "到达最右一个继续往右滑....")
                                //若还有下一个bottom fragment页面则切换
                                mainViewPager.currentItem.takeIf { it < mainViewPager.adapter!!.itemCount - 1 }
                                    ?.also { (activity as MainActivity).switchFragment(it + 1) }
                            }
                        }
                    }
                    oldPositon = currentPosition
                }
            }

            //页面滚动的位置信息回调： position 当前滚动到哪个页面，positionOffset 位置偏移百分比, positionOffsetPixels 当前所在页面偏移量
            //此回调会触发完onPageScrollStateChanged 的 state 值为1时后面才触发回调
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                currentPosition = position
            }
        })
    }
}