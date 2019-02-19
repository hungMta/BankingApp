package com.hungtran.bankingassistant.ui.maket.interestRate

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.RadioGroup
import com.hungtran.bankingassistant.R
import com.hungtran.bankingassistant.ui.maket.interestRate.enterprise.EnterpriseInterestRateFragment
import com.hungtran.bankingassistant.ui.maket.interestRate.personal.PersonalInterestRateFragment
import com.hungtran.bankingassistant.util.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_interest_rate.*
import kotlinx.android.synthetic.main.fragment_rate.*

class InterestRateFragment: BaseFragment(), RadioGroup.OnCheckedChangeListener {

    lateinit var fragmentTransaction: FragmentTransaction
    lateinit var personalInterestRateFragment: PersonalInterestRateFragment
    lateinit var enterpriseInterestRateFragment: EnterpriseInterestRateFragment
    override fun getLayoutId(): Int {
       return R.layout.fragment_interest_rate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personalInterestRateFragment = PersonalInterestRateFragment()
        enterpriseInterestRateFragment = EnterpriseInterestRateFragment()


        rateSegmented.setTintColor(Color.parseColor("#008577"))
        rateSegmented.setOnCheckedChangeListener(this)
        btnRatePersonalRate.isChecked = true
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.btnRatePersonalRate -> setFragment(personalInterestRateFragment)
            R.id.btnRateEnterpriseRate -> setFragment(enterpriseInterestRateFragment)
        }
    }

    fun setFragment(fragment: Fragment) {
        fragmentTransaction = fragmentManager!!.beginTransaction()
       if (fragment.isAdded) {
           fragmentTransaction.show(fragment)
       } else {
           fragmentTransaction.add(R.id.rateFrameLayout, fragment)
       }
        fragmentTransaction.commit()
    }

}