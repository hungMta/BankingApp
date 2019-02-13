package com.hungtran.bankingassistant.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log.println
import com.hungtran.bankingassistant.ui.map.MapFragment
import com.hungtran.bankingassistant.ui.news.NewsFragment
import com.hungtran.bankingassistant.ui.rate.RateFragment
import com.hungtran.bankingassistant.ui.setting.SettingFragment

/**
 * Created by hungtd on 2/18/19.
 */
class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var fragmentManager: FragmentManager

    init {
        this.fragmentManager = fm
    }

    override fun getItem(p0: Int): Fragment {
        var fragment = Fragment()
        when (p0) {
            0 -> fragment = NewsFragment()
            1 -> fragment = RateFragment()
            2 -> fragment = MapFragment()
            3 -> fragment = SettingFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 4
    }
}