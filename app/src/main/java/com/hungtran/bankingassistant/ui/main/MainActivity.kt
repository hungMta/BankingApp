package com.hungtran.bankingassistant.ui.main

import android.os.Bundle

import com.hungtran.bankingassistant.R
import com.hungtran.bankingassistant.adapters.ViewPagerAdapter
import com.hungtran.bankingassistant.util.base.BaseActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_comment_black_24dp)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_attach_money_black_24dp)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_place_black_24dp)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.ic_person_black_24dp)
    }
}
