package com.hungtran.bankingassistant.ui.main

import com.hungtran.bankingassistant.util.BasePresenter
import com.hungtran.bankingassistant.util.BaseView

/**
 * Created by hungtd on 2/18/19.
 */

interface MainActivityContract {
    interface View : BaseView<Presenter> {
        fun a()
        fun b(c: Int)

    }

    interface Presenter : BasePresenter
}
