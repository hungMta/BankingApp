package com.hungtran.bankingassistant.ui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.adapters.AreaRecylerViewAdapter;
import com.hungtran.bankingassistant.adapters.BranchSearchRecyclerViewAdapter;
import com.hungtran.bankingassistant.adapters.FilterBankRecyclerViewAdapter;
import com.hungtran.bankingassistant.dialog.AreaDialog;
import com.hungtran.bankingassistant.model.area.Area;
import com.hungtran.bankingassistant.model.area.AreaResponse;
import com.hungtran.bankingassistant.model.bankLocation.AvaiableBankLocationResponse;
import com.hungtran.bankingassistant.model.bankLocation.Bank;
import com.hungtran.bankingassistant.model.bankLocation.BankLocation;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationRequestBody;
import com.hungtran.bankingassistant.model.bankLocation.BankLocationResponse;
import com.hungtran.bankingassistant.model.bankLocation.BranchLocation;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.ImageHelper;
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

public class MapActivity extends BaseActivity implements AreaDialog.AreaDialogListener,
        AreaRecylerViewAdapter.AreaOnItemClick,
        View.OnClickListener,
        MapConstract.View,
        OnMapReadyCallback,
        BranchSearchRecyclerViewAdapter.OnBranchRecyclerViewApdapterListener,
        FilterBankRecyclerViewAdapter.FilterBankListener {
    private static final Integer FINE_LOCATION_CODE = 10101;
    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;

    @BindView(R.id.recylerViewBranch)
    RecyclerView mRecyclerViewBranch;

    @BindView(R.id.layoutFilter)
    LinearLayout mLayoutFilter;

    @BindView(R.id.layoutSearchBranch)
    LinearLayout mLayoutSearchBranch;

    @BindView(R.id.layoutSearchATM)
    LinearLayout mLayoutSearchAtm;

    @BindView(R.id.imgSearchATMCheck)
    ImageView mImgSearchATMCheck;

    @BindView(R.id.imgSearchBranchCheck)
    ImageView mImgSearchBranchCheck;

    @BindView(R.id.layoutProgressBar)
    RelativeLayout mLayoutProgressBar;

    @BindView(R.id.edtSearch)
    EditText mEdtSearch;

    @BindView(R.id.txtArea)
    TextView mTxtArea;

    @BindView(R.id.recyclerBankList)
    RecyclerView mRecylerBankList;

    LatLng myLocation;
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
    private int isAtm = 1;
    private int isBranch = 1;
    private int idBank = 0;
    private int typeSearch = Constant.TYPE_SEARCH_LOCATION;
    private String addressEdtSearchText;
    private String address;
    private AreaResponse areaResponse;
    private Area currentArea = new Area();
    private List<Bank> avaiableBankList;
    private FilterBankRecyclerViewAdapter filterBankRecyclerViewAdapter;


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
        mPresenter.getArea();
        mPresenter.getAvaibleBankLocation();
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


        mLayoutSearchAtm.setOnClickListener(this);
        mLayoutSearchBranch.setOnClickListener(this);
        mLayoutFilter.setVisibility(View.GONE);
        setupEditTextSearch();
        mTxtArea.setOnClickListener(this);
        AreaRecylerViewAdapter.setAreaOnItemClick(this);
        setupAvaibleBankLocationRecyclerView();
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
                showProgress(true);
                searchBank();
            } else {
                mLayoutFilter.setVisibility(View.VISIBLE);
                isShowedLayoutFilter = true;
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
        for (int i = 0; i < markerList.size(); i++) {
            if (markerList.get(i).getSnippet().equals(branchLocation.getAddress())) {
                markerList.get(i).showInfoWindow();
                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutSearchATM:
                if (isAtm == 1) {
                    isAtm = 0;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mImgSearchATMCheck.setImageDrawable(getDrawable(R.drawable.ic_check_circle_gray));
                    } else {
                        mImgSearchATMCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_gray));
                    }
                } else {
                    isAtm = 1;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mImgSearchATMCheck.setImageDrawable(getDrawable(R.drawable.ic_check_circle));
                    } else {
                        mImgSearchATMCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle));
                    }
                }
                break;
            case R.id.layoutSearchBranch:
                if (isBranch == 1) {
                    isBranch = 0;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mImgSearchBranchCheck.setImageDrawable(getDrawable(R.drawable.ic_check_circle_gray));
                    } else {
                        mImgSearchBranchCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_gray));
                    }
                } else {
                    isBranch = 1;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mImgSearchBranchCheck.setImageDrawable(getDrawable(R.drawable.ic_check_circle));
                    } else {
                        mImgSearchBranchCheck.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle));
                    }
                }
                break;
            case R.id.txtArea:
                showAreaDialog();
                break;
        }
    }

    @Override
    public void areaOnItemClick(Area area) {
        currentArea = area;
    }

    @Override
    public void onAreaDialogDestroy(Area area) {
        currentArea = area;
        if (currentArea != null) {
            if (currentArea.getId() == 0 && currentArea.getIdArea() == 0) {
                typeSearch = Constant.TYPE_SEARCH_LOCATION;
            } else {
                typeSearch = Constant.TYPE_SEARCH_AREA;
            }
            showProgress(true);
            searchBank();
            mTxtArea.setText(currentArea.getName());
        }
    }

    @Override
    public void onChooseBank(Bank bank) {
        idBank = bank.getId();
        for (int i = 0; i < avaiableBankList.size(); i++) {
            if (idBank == avaiableBankList.get(i).getId()) {
                avaiableBankList.get(i).setChecked(true);
            } else {
                avaiableBankList.get(i).setChecked(false);
            }
        }
        filterBankRecyclerViewAdapter.updateAdapter(avaiableBankList);
    }


    public interface OnMapActivityListener {
        void onMapActivtyDestroy();
    }

    public static void setOnMapActivityListener(OnMapActivityListener listener) {
        mOnMapActivityListener = listener;
    }

    private void setupRecyclerView() {
        mBranchSearchRecyclerViewAdapter = new BranchSearchRecyclerViewAdapter(new ArrayList<>(), null);
        mBranchSearchRecyclerViewAdapter.setOnBranchRecyclerViewApdapterListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewBranch.setLayoutManager(linearLayoutManager);
        mRecyclerViewBranch.setAdapter(mBranchSearchRecyclerViewAdapter);
    }


    private void redirectToCurrentLocation(Location location) {
        LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
        this.myLocation = myLocation;
        markMyLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, (float) 13.0));
        searchBank();
    }

    private void searchBank() {
        BankLocationRequestBody bankLocationRequestBody = new BankLocationRequestBody();
        bankLocationRequestBody.setId(idBank);
        bankLocationRequestBody.setType(typeSearch);
        bankLocationRequestBody.setAtm(isAtm);
        bankLocationRequestBody.setBranch(isBranch);
        bankLocationRequestBody.setLatitude(myLocation.latitude);
        bankLocationRequestBody.setLongitude(myLocation.longitude);
        bankLocationRequestBody.setDistrict(currentArea.getName());
        bankLocationRequestBody.setCity("Hà Nội");
        bankLocationRequestBody.setAddress(addressEdtSearchText);
        mPresenter.searchBankPosition(bankLocationRequestBody);
    }

    @Override
    public void searchBankPositionResult(BankLocationResponse bankLocationResponse) {
        showProgress(false);
        mMap.clear();
        markMyLocation();
        List<BranchLocation> branchLocationList = new ArrayList<>();
        Toast.makeText(this, "success + " + bankLocationResponse.getBankLocations().size(), Toast.LENGTH_SHORT).show();
        if (bankLocationResponse.getBankLocations().size() == 0) {
            mBranchSearchRecyclerViewAdapter.updateAdapter(null, null);
            return;
        }
        try {
            markerList = new ArrayList<>();
            BitmapDescriptor bmBranchPlace = ImageHelper.getBranchPlaceIcon(this);
            BitmapDescriptor bmAtmPlace = ImageHelper.getAtmPlaceIcon(this);
            for (BankLocation bankLocation : bankLocationResponse.getBankLocations()) {
                List<BranchLocation> list = bankLocation.getBranchLocations();
                branchLocationList.addAll(list);
                String bankName = bankLocation.getName();
                for (int i = 0; i < list.size(); i++) {
                    BranchLocation branchLocation = list.get(i);
                    LatLng loc = new LatLng(branchLocation.getLatitude(), branchLocation.getLongtitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    if (branchLocation.getType() == 1) { // atm
                        markerOptions.icon(bmAtmPlace);
                    } else { // branch
                        markerOptions.icon(bmBranchPlace);
                    }
                    markerOptions.position(loc);
                    markerOptions.title(bankName + " - " + branchLocation.getName());
                    markerOptions.snippet(branchLocation.getAddress());
                    markerList.add(mMap.addMarker(markerOptions));
                    if (i == 0) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, (float) 13.0));
                    }
                }
            }

            mBranchSearchRecyclerViewAdapter.updateAdapter(branchLocationList, bankLocationResponse.getBankLocations());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getAreaSuccess(AreaResponse areaResponse) {
        this.areaResponse = areaResponse;
    }

    @Override
    public void getAvaiableBankLocationSuccess(AvaiableBankLocationResponse avaiableBankLocationResponse) {
        avaiableBankList = avaiableBankLocationResponse.getBanks();
        filterBankRecyclerViewAdapter.updateAdapter(avaiableBankList);
    }

    private void setupAvaibleBankLocationRecyclerView() {
        filterBankRecyclerViewAdapter = new FilterBankRecyclerViewAdapter(this, avaiableBankList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecylerBankList.setLayoutManager(layoutManager);
        mRecylerBankList.setAdapter(filterBankRecyclerViewAdapter);
        FilterBankRecyclerViewAdapter.setFilterBankListener(this);
    }

    @Override
    public void hideProgress() {
        showProgress(false);
    }

    private void markMyLocation() {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.icon(ImageHelper.getMyPlaceIcon(this));
        markerOptions.position(myLocation);
        mMap.addMarker(markerOptions);
    }


    private void setupEditTextSearch() {
        mEdtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addressEdtSearchText = v.getText().toString();
                    typeSearch = Constant.TYPE_SEARCH_KEY;
                    showProgress(true);
                    searchBank();
                    handled = true;
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(mEdtSearch.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return handled;
            }
        });
    }

    private void showProgress(boolean isShow) {
        if (isShow) {
            mLayoutProgressBar.setVisibility(View.VISIBLE);
        } else {
            mLayoutProgressBar.setVisibility(View.GONE);
        }
    }

    private void showAreaDialog() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        fragmentTransaction.addToBackStack(null);

        // Create and show the dialog.
        AreaDialog areaDialog = AreaDialog.newInstance(areaResponse);
        areaDialog.show(fragmentTransaction, "dialog");
        AreaDialog.setAreaDialogListener(this);
    }
}
