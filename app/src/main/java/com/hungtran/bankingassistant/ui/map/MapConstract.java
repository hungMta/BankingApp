package com.hungtran.bankingassistant.ui.map;

import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequestBody;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationResponse;

/**
 * Created by hungtd on 2/18/19.
 */

public interface MapConstract {

    interface View {
        void searchBankPositionResult(BankLocationResponse bankLocationResponse);
    }

    interface Presenter {
        void searchBankPosition(BankLocationRequestBody bankLocationRequestBody);
    }
}
