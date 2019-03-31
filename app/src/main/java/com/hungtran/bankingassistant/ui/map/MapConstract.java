package com.hungtran.bankingassistant.ui.map;

import com.hungtran.bankingassistant.model.area.AreaResponse;
import com.hungtran.bankingassistant.model.bankLocation.AvaiableBankLocationResponse;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequestBody;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationResponse;

/**
 * Created by hungtd on 2/18/19.
 */

public interface MapConstract {

    interface View {
        void searchBankPositionResult(BankLocationResponse bankLocationResponse);

        void getAreaSuccess(AreaResponse areaResponse);

        void getAvaiableBankLocationSuccess(AvaiableBankLocationResponse avaiableBankLocationResponse);

        void hideProgress();
    }

    interface Presenter {
        void searchBankPosition(BankLocationRequestBody bankLocationRequestBody);

        void getArea();

        void getAvaibleBankLocation();
    }
}
