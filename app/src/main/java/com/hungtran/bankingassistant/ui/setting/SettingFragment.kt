package com.hungtran.bankingassistant.ui.setting

import android.os.Bundle
import android.view.View

import com.hungtran.bankingassistant.R
import com.hungtran.bankingassistant.ui.maket.MaketFragment
import com.hungtran.bankingassistant.util.base.BaseFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

/**
 * Created by hungtd on 2/18/19.
 */

class SettingFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FragmentPagerItemAdapter(
                fragmentManager, FragmentPagerItems.with(context)
                .add("", MaketFragment::class.java)
                .create())
    }
}
