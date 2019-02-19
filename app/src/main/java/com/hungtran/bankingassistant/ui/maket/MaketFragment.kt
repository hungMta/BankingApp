package com.hungtran.bankingassistant.ui.maket

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.RadioGroup
import com.hungtran.bankingassistant.R
import com.hungtran.bankingassistant.adapters.RateViewPagerAdapter
import com.hungtran.bankingassistant.util.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_rate.*

/**
 * Created by hungtd on 2/18/19.
 */

class MaketFragment : BaseFragment(), ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    override fun getLayoutId(): Int {
        return R.layout.fragment_rate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        segmented.setTintColor(Color.parseColor("#008577"))
        segmented.setOnCheckedChangeListener(this)
        btnInterestRate.isChecked = true
        val rateViewPagerAdapter = RateViewPagerAdapter(fragmentManager!!)
        rateViewPager.adapter = rateViewPagerAdapter
        rateViewPager.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

    }

    override fun onPageSelected(p0: Int) {
      when(p0) {
          0 -> btnInterestRate.isChecked = true
          1 -> btnExchangeRate.isChecked = true
          2 -> btnGold.isChecked = true
      }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId) {
            R.id.btnInterestRate -> rateViewPager.setCurrentItem(0,true)
            R.id.btnExchangeRate -> rateViewPager.setCurrentItem(1,true)
            R.id.btnGold -> rateViewPager.setCurrentItem(2,true)
        }
    }

}
