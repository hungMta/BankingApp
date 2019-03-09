package com.hungtran.bankingassistant.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.DefaultPopupWindowRecyerViewAdapter;
import com.hungtran.bankingassistant.model.BaseModel;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.PopupHelper;
import com.hungtran.bankingassistant.util.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 3/7/19.
 */

public class CalculatorFragment extends BaseFragment implements View.OnClickListener {
    private static CalculatorFragment instance;

    @BindView(R.id.edtSavingMoney)
    EditText mEdtSavingMoney;

    @BindView(R.id.edtInterestRate)
    EditText mEdtInterestRate;

    @BindView(R.id.edtTerm)
    EditText mEdtTerm;

    @BindView(R.id.edtTimeSaving)
    EditText mEdtTimeSaving;

    @BindView(R.id.btnWithdrawType)
    Button mBtnWithdrawType;

    @BindView(R.id.btnRefresh)
    Button mBtnRefresh;

    @BindView(R.id.layoutTimeSavingType)
    LinearLayout mLayoutTimeSavingType;

    @BindView(R.id.txtTimeSavingType)
    TextView mTxtTimeSavingType;

    private PopupWindow mPopupTimeSavingType;
    private PopupWindow mPopupWithdrawType;
    private List<BaseModel> mListPopupTimeSavingModel;
    private List<BaseModel> mListPopupWithdrawType;
    private int mTimeSavingType = Constant.TYPE_SAVING_MONTH;
    private int mWithdrawType = Constant.TYPE_SAVING_WITHDRAW;

    public static CalculatorFragment getInstance() {
        if (instance == null) {
            instance = new CalculatorFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_caluclator;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupPopupModel();
        mBtnRefresh.setOnClickListener(this);
        mBtnWithdrawType.setOnClickListener(this);
        mLayoutTimeSavingType.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWithdrawType:
                DefaultPopupWindowRecyerViewAdapter adapterWithdraw = new DefaultPopupWindowRecyerViewAdapter(mListPopupWithdrawType);
                mPopupWithdrawType = PopupHelper.getDefautPopup(getContext(), adapterWithdraw, v.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
                mPopupWithdrawType.showAsDropDown(v, 0, -50);
                adapterWithdraw.setOnItemClick(new DefaultPopupWindowRecyerViewAdapter.OnItemClick() {
                    @Override
                    public void onItemDefaultClicked(BaseModel baseModel) {
                        mWithdrawType = baseModel.getId();
                        mBtnWithdrawType.setText(baseModel.getName());
                        mPopupWithdrawType.dismiss();
                    }
                });
                break;
            case R.id.layoutTimeSavingType:
                DefaultPopupWindowRecyerViewAdapter adapterTimeSaving = new DefaultPopupWindowRecyerViewAdapter(mListPopupTimeSavingModel);
                mPopupTimeSavingType = PopupHelper.getDefautPopup(getContext(), adapterTimeSaving, 500, LinearLayout.LayoutParams.WRAP_CONTENT);
                mPopupTimeSavingType.showAsDropDown(v, 0, -50);
                adapterTimeSaving.setOnItemClick(new DefaultPopupWindowRecyerViewAdapter.OnItemClick() {
                    @Override
                    public void onItemDefaultClicked(BaseModel baseModel) {
                        mTimeSavingType = baseModel.getId();
                        mTxtTimeSavingType.setText(baseModel.getName());
                        mPopupTimeSavingType.dismiss();
                    }
                });
                break;
            case R.id.btnRefresh:
                break;
            default:
                break;
        }
    }

    private void setupPopupModel() {
        mListPopupTimeSavingModel = new ArrayList<>();
        mListPopupTimeSavingModel.add(new BaseModel(Constant.TYPE_SAVING_MONTH, getResources().getString(R.string.month)));
        mListPopupTimeSavingModel.add(new BaseModel(Constant.TYPE_SAVING_YEAR, getResources().getString(R.string.year)));

        mListPopupWithdrawType = new ArrayList<>();
        mListPopupWithdrawType.add(new BaseModel(Constant.TYPE_SAVING_WITHDRAW, getResources().getString(R.string.type_with_draw)));
        mListPopupWithdrawType.add(new BaseModel(Constant.TYPE_SAVING_WITHDRAW_END, getResources().getString(R.string.type_with_draw_end)));
    }
}
