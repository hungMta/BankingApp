package com.hungtran.bankingassistant.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.AreaRecylerViewAdapter;
import com.hungtran.bankingassistant.model.area.Area;
import com.hungtran.bankingassistant.model.area.AreaResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaDialog extends DialogFragment implements AreaRecylerViewAdapter.AreaOnItemClick {
    private static final String AREA_KEY = "AREA_LIST";
    private AreaResponse areaResponse;
    private AreaRecylerViewAdapter adapter;
    private static AreaDialogListener areaDialogListener;

    @BindView(R.id.recylerArea)
    RecyclerView mRecyclerArea;

    @BindView(R.id.imgClose)
    ImageView mImgClose;


    public static AreaDialog newInstance(AreaResponse areaResponse) {
        AreaDialog areaDialog = new AreaDialog();
        Bundle args = new Bundle();
        args.putSerializable(AREA_KEY, areaResponse);
        areaDialog.setArguments(args);
        return areaDialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_area, container, false);
        ButterKnife.bind(this, view);
        areaResponse = (AreaResponse) getArguments().getSerializable(AREA_KEY);
        setupRecyclerView();
        mImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setupRecyclerView() {
        if (areaResponse != null) {
            adapter = new AreaRecylerViewAdapter(areaResponse.getAreaList());
            LinearLayoutManager linearLayoutManager =
                    new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRecyclerArea.setLayoutManager(linearLayoutManager);
            mRecyclerArea.setAdapter(adapter);
            AreaRecylerViewAdapter.setAreaOnItemClick(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void areaOnItemClick(Area area) {
        areaDialogListener.onAreaDialogDestroy(area);
        dismiss();
    }


    public interface AreaDialogListener {
        void onAreaDialogDestroy(Area area);
    }

    public static void setAreaDialogListener(AreaDialogListener listener) {
        areaDialogListener = listener;
    }
}
