package com.hungtran.bankingassistant.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager

import com.hungtran.bankingassistant.R
import com.hungtran.bankingassistant.adapters.ViewPagerAdapter
import com.hungtran.bankingassistant.util.base.BaseActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupTabbar()
    }

    fun setupTabbar() {
        val viewPagerAdapter = ViewPagerAdapter(getSupportFragmentManager())
        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.tab_news_selector)?.setText("Tin tức")
        tabLayout.getTabAt(1)?.setIcon(R.drawable.tab_rate_selector)?.setText("Thị trường")
        tabLayout.getTabAt(2)?.setIcon(R.drawable.tab_place_selector)?.setText("Vị trí")
        tabLayout.getTabAt(3)?.setIcon(R.drawable.tab_setting_selector)?.setText("Tiền ảo")
        tabLayout.getTabAt(4)?.setIcon(R.drawable.tab_setting_selector)?.setText("Cài đặt")
        tabLayout.addOnTabSelectedListener(this)
        viewPager.addOnPageChangeListener(this)
    }

    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onTabSelected(p0: TabLayout.Tab?) {

    }


    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {

    }

    override fun onPageScrollStateChanged(p0: Int) {

    }
}
