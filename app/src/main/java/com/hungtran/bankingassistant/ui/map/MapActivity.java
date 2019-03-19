package com.hungtran.bankingassistant.ui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.BranchSearchRecyclerViewAdapter;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequestBody;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationResponse;
import com.hungtran.bankingassistant.model.bankLocation.BranchLocation;
import com.hungtran.bankingassistant.util.base.BaseActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by hungtd on 2/18/19.
 */

public class MapActivity extends BaseActivity implements MapConstract.View, OnMapReadyCallback, BranchSearchRecyclerViewAdapter.OnBranchRecyclerViewApdapterListener{
    private static final Integer FINE_LOCATION_CODE = 10101;
    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;

    @BindView(R.id.recylerViewBranch)
    RecyclerView mRecyclerViewBranch;

    @BindView(R.id.layoutFilter)
    LinearLayout mLayoutFilter;

    SupportMapFragment mMapFragment;
    private GoogleMap mMap;

    private BranchSearchRecyclerViewAdapter mBranchSearchRecyclerViewAdapter;
    private static OnMapActivityListener mOnMapActivityListener;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private SupportMapFragment mapFragment;
    private Location mMyLocation;
    private boolean isShowedLayoutFilter;
    private MapPresenter mPresenter;
    private BankLocationResponse mBankLocationResponse;
    private List<Marker> markerList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mPresenter = new MapPresenter(this);
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
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onMapReady: permission");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    FINE_LOCATION_CODE);
        } else {
            mapFragment.getMapAsync(this);
        }
        mapFragment.getMapAsync(this);
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
            if (isShowedLayoutFilter) {
                isShowedLayoutFilter = false;
                mLayoutFilter.setVisibility(View.GONE);
            } else {
                isShowedLayoutFilter = true;
                mLayoutFilter.setVisibility(View.VISIBLE);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == FINE_LOCATION_CODE) {
            mapFragment.getMapAsync(this);
        }
    }

    @SuppressLint({"MissingPermission", "CheckResult"})
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ObservableOnSubscribe<Location> handler = emitter -> {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    mMyLocation = location;
                    emitter.onNext(mMyLocation);
                    emitter.onComplete();
                }
            });
        };

        Observable<Location> observable = Observable.create(handler);
        observable.subscribe(this::redirectToCurrentLocation,
                error -> Log.d(TAG, "onMapReady: error " + error)
        );
    }

    @Override
    public void onItemBranchReyclerViewAdapterClicked(BranchLocation branchLocation) {
        LatLng latLng = new LatLng(branchLocation.getLatitude(), branchLocation.getLongtitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, (float) 13.0));
        for (int i = 0; i < markerList.size(); i ++) {
            if (markerList.get(i).getSnippet().equals(branchLocation.getAddress()) && markerList.get(i).getTitle().equals(branchLocation.getName())) {
                markerList.get(i).showInfoWindow();
                break;
            }
        }
    }


    public interface OnMapActivityListener {
        void onMapActivtyDestroy();
    }

    public static void setOnMapActivityListener(OnMapActivityListener listener) {
        mOnMapActivityListener = listener;
    }

    private void setupRecyclerView() {
        mBranchSearchRecyclerViewAdapter = new BranchSearchRecyclerViewAdapter(new ArrayList<>());
        mBranchSearchRecyclerViewAdapter.setOnBranchRecyclerViewApdapterListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewBranch.setLayoutManager(linearLayoutManager);
        mRecyclerViewBranch.setAdapter(mBranchSearchRecyclerViewAdapter);
    }


    private void redirectToCurrentLocation(Location location) {
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
        markerOptions.position(myLocation);
        markerOptions.title("My location haha hahah ahah hahah ha hah 213 21123 1  sdf asdf sadf asdf saf df");
        markerOptions.snippet("123123 12312 12312312 123");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, (float) 13.0));

        BankLocationRequestBody bankLocationRequestBody = new BankLocationRequestBody();
        bankLocationRequestBody.setId(0);
        bankLocationRequestBody.setLatitude(myLocation.latitude);
        bankLocationRequestBody.setLongitude(myLocation.longitude);
        bankLocationRequestBody.setCity("Hà Nội");
        mPresenter.searchBankPosition(bankLocationRequestBody);
    }

    @Override
    public void searchBankPositionResult(BankLocationResponse bankLocationResponse) {
        try {
            List<BranchLocation> branchLocationList = bankLocationResponse.getBankLocations().get(0).getBranchLocations();
            mBranchSearchRecyclerViewAdapter.updateAdapter(branchLocationList);
            markerList = new ArrayList<>();
            for (BranchLocation location: branchLocationList) {
                LatLng loc = new LatLng(location.getLatitude(), location.getLongtitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                markerOptions.position(loc);
                markerOptions.title(location.getName());
                markerOptions.snippet(location.getAddress());
                markerList.add(mMap.addMarker(markerOptions));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
}
