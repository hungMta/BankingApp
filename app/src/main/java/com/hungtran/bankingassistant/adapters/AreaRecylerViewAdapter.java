package com.hungtran.bankingassistant.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.area.Area;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Area> areaList;
    private static AreaOnItemClick onItemClick;

    public AreaRecylerViewAdapter(List<Area> areaList) {
        this.areaList = areaList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_area_recyler_view, viewGroup, false);
        return new AreaItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Area area;
        if (i == 0) {
            area = new Area(0, 0, "Địa điểm gần đây", null);
        } else {
            area = areaList.get(i - 1);
        }

        ((AreaItem) viewHolder).mTxtArea.setText(area.getName());
        ((AreaItem) viewHolder).mLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.areaOnItemClick(area);
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaList == null ? 0 : areaList.size() + 1;
    }

    public class AreaItem extends RecyclerView.ViewHolder {

        @BindView(R.id.txtArea)
        TextView mTxtArea;

        @BindView(R.id.layoutAreaItem)
        LinearLayout mLayoutItem;

        public AreaItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AreaOnItemClick {
        void areaOnItemClick(Area area);
    }

    public static void setAreaOnItemClick(AreaOnItemClick click) {
        onItemClick = click;
    }
}
