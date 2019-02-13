package com.hungtran.bankingassistant.util;

/**
 * Created by hungtd on 2/18/19.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
