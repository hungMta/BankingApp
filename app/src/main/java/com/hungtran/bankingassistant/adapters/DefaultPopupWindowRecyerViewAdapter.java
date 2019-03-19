package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.base.BaseModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DefaultPopupWindowRecyerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClick mOnItemClick;
    private List<BaseModel> list;

    public DefaultPopupWindowRecyerViewAdapter(List<BaseModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_default_popup_reycler_view, viewGroup, false);
        return new DefaultPopupItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((DefaultPopupItem) viewHolder).txtName.setText(list.get(i).getName());
        ((DefaultPopupItem) viewHolder).layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    mOnItemClick.onItemDefaultClicked(list.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class DefaultPopupItem extends RecyclerView.ViewHolder {

        @BindView(R.id.layoutItem)
        RelativeLayout layout;

        @BindView(R.id.txtName)
        TextView txtName;

        public DefaultPopupItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClick {
        void onItemDefaultClicked(BaseModel baseModel);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.mOnItemClick = onItemClick;
    }

}
