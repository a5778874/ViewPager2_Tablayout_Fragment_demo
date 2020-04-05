package com.zzh.viewpager2tabfragment.fragment.family

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
 * 底部菜单 家庭 fragment
 */
class BottomFamilyFragment : Fragment(R.layout.fragment_bottom_family) {
    private val tabTitleList = arrayOf("租客", "房间", "房子")

    private val tabFragmentList = arrayListOf<Fragment>()
    private val tabRoomerFragment by lazy { TabRoomerFragment() } // 租客
    private val tabRoomFragment by lazy { TabRoomFragment() } // 房间
    private val tabHouseFragment by lazy { TabHouseFragment() } // 房子

    private lateinit var mainViewPager: ViewPager2 // 父ViewPager



    init {
        tabFragmentList.run {
            add(tabRoomerFragment)
            add(tabRoomFragment)
            add(tabHouseFragment)
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
            private var currentPosition = 0
            private var oldPositon = 0

            override fun onPageScrollStateChanged(state: Int) {
                if (state == 0) {
                    if (currentPosition == oldPositon) {
                        when (currentPosition) {
                            0 -> {
                                Log.d("TAG1", "到达最左一个....")
                                //若还有上一个bottom fragment页面则切换
                                mainViewPager.currentItem.takeIf { it > 0 }
                                    ?.also { (activity as MainActivity).switchFragment(it - 1) }
                            }
                            (viewPager.adapter as FragmentStateAdapter).itemCount - 1 -> {
                                Log.d("TAG1", "到达最右一个....")
                                //若还有下一个bottom fragment页面则切换
                                mainViewPager.currentItem.takeIf { it < mainViewPager.adapter!!.itemCount - 1 }
                                    ?.also { (activity as MainActivity).switchFragment(it + 1) }
                            }
                        }
                    }
                    oldPositon = currentPosition
                }
            }

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