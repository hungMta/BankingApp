package com.hungtran.bankingassistant.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hungtran.bankingassistant.ui.maket.exchangeRate.ExchangeRateFragment
import com.hungtran.bankingassistant.ui.maket.gold.GoldFragment
import com.hungtran.bankingassistant.ui.maket.interestRate.InterestRateFragment

class RateViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(p0: Int): Fragment {
        var fragment = Fragment()
        when (p0) {
            0 -> fragment = InterestRateFragment()
            1 -> fragment = ExchangeRateFragment()
            2 -> fragment = GoldFragment()
        }
        return fragment
    }

}