package com.zzh.viewpager2tabfragment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.zzh.viewpager2tabfragment.fragment.family.BottomFamilyFragment
import com.zzh.viewpager2tabfragment.fragment.home.BottomHomeFragment
import com.zzh.viewpager2tabfragment.R
import com.zzh.viewpager2tabfragment.fragment.BottomInviteFragment
import com.zzh.viewpager2tabfragment.fragment.BottomMineFragment
import kotlinx.android.synthetic.main.activity_main.*

//AndroidX AppCompat 1.1.0 开始 AppCompatActivity构造函数可直接传入布局ID
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val fragmentList = arrayListOf<Fragment>()
    private val homeFragment by lazy { BottomHomeFragment() } // 首页
    private val statisticsFragment by lazy { BottomFamilyFragment() } // 统计
    private val inviteFragment by lazy { BottomInviteFragment() } // 邀请
    private val mineFragment by lazy { BottomMineFragment() } // 我的

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun initView() {
        fragmentList.run {
            add(homeFragment)
            add(statisticsFragment)
            add(inviteFragment)
            add(mineFragment)
        }
        initViewPager()
        bottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> switchFragment(0)
                R.id.family -> switchFragment(1)
                R.id.invite -> switchFragment(2)
                R.id.mine -> switchFragment(3)
            }
            true
        }
    }

    fun switchFragment(index: Int) {
        mainViewpager.setCurrentItem(index, true)
    }

    private fun initViewPager() {
        //父ViewPager页面切换监听
        mainViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                //滑动fragment对应底部按菜单钮选中
                bottomNavView.menu.getItem(position).isChecked = true
                //除了前2个bottom fragment有viewpager嵌套不处理滑动，其他都处理滑动
                mainViewpager.isUserInputEnabled =(mainViewpager.currentItem > 1)
            }
        })
        mainViewpager.offscreenPageLimit = 2
        mainViewpager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = fragmentList[position]
            override fun getItemCount() = fragmentList.size
        }
    }

    fun getMainViewPager(): ViewPager2 {
        return mainViewpager
    }

}
