package com.hungtran.bankingassistant.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.BranchSearchRecyclerViewAdapter;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hungtd on 2/18/19.
 */

public class MapActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;

    @BindView(R.id.recylerViewBranch)
    RecyclerView mRecyclerViewBranch;

    private BranchSearchRecyclerViewAdapter mBranchSearchRecyclerViewAdapter;
    private static OnMapActivityListener mOnMapActivityListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(null);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMapActivityListener != null) {
                    mOnMapActivityListener.onMapActivtyDestroy();
                }
                finish();
            }
        });
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setupRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map_menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Toast.makeText(MapActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public interface OnMapActivityListener {
        void onMapActivtyDestroy();
    }

    public static void setOnMapActivityListener(OnMapActivityListener listener){
        mOnMapActivityListener = listener;
    }

    private void setupRecyclerView(){
        mBranchSearchRecyclerViewAdapter = new BranchSearchRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewBranch.setLayoutManager(linearLayoutManager);
        mRecyclerViewBranch.setAdapter(mBranchSearchRecyclerViewAdapter);
    }
}
