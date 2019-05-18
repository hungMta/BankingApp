package com.hungtran.bankingassistant.ui.setting;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hungtran.bankingassistant.R;
import com.hungtran.bankingassistant.model.user.User;
import com.hungtran.bankingassistant.ui.calculator.CalculatorActivity;
import com.hungtran.bankingassistant.ui.changePassword.ChangePasswordAcitvity;
import com.hungtran.bankingassistant.ui.login.LoginActivty;
import com.hungtran.bankingassistant.ui.map.MapActivity;
import com.hungtran.bankingassistant.ui.wallet.WalletActivity;
import com.hungtran.bankingassistant.util.Constant;
import com.hungtran.bankingassistant.util.base.BaseFragment;
import com.hungtran.bankingassistant.util.base.SharePreference;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "HUNGTD";
    private static final int PICK_IMAGE = 987;
    private static final int READ_STORAGE = 123;
    @BindView(R.id.layoutCalculator)
    LinearLayout mLayoutCalculator;

    @BindView(R.id.layoutChangePassword)
    LinearLayout mLayoutChangePassword;

    @BindView(R.id.layoutLogout)
    LinearLayout mLayoutLogout;

    @BindView(R.id.layoutAcccountBank)
    LinearLayout mLayoutAccountBank;

    @BindView(R.id.layoutMapSearch)
    LinearLayout mLayoutMapSearch;

    @BindView(R.id.txtName)
    TextView mTxtName;

    @BindView(R.id.txtPhoneNumber)
    TextView mTxtPhonenumber;

    @BindView(R.id.imgAvata)
    ImageView mImageAvata;

    private static SettingFragment instance;
    private User user;

    public static SettingFragment getInstance() {
        if (instance == null) {
            instance = new SettingFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mLayoutCalculator.setOnClickListener(this);
        mLayoutChangePassword.setOnClickListener(this);
        mLayoutLogout.setOnClickListener(this);
        mLayoutAccountBank.setOnClickListener(this);
        mLayoutMapSearch.setOnClickListener(this);
        mImageAvata.setOnClickListener(this);

        user = SharePreference.getObjectVal(Constant.USER_KEY, User.class);
        mTxtName.setText(user.getName());
        mTxtPhonenumber.setText(user.getPhone());
        Log.d(TAG, "onViewCreated: " + user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutCalculator:
                Intent intent = new Intent(getContext(), CalculatorActivity.class);
                startActivity(intent);
                break;
            case R.id.layoutChangePassword:
                Intent intent1 = new Intent(getContext(), ChangePasswordAcitvity.class);
                startActivity(intent1);
                break;
            case R.id.layoutLogout:
                Intent intent2 = new Intent(getContext(), LoginActivty.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                break;
            case R.id.layoutAcccountBank:
                Intent intent4 = new Intent(getContext(), WalletActivity.class);
                startActivity(intent4);
                break;
            case R.id.layoutMapSearch:
                Intent intent3 = new Intent(getContext(), MapActivity.class);
                startActivity(intent3);
                break;
            case R.id.imgAvata:
                checkPermisionPickImage();
                break;
        }
    }

    private void checkPermisionPickImage(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_STORAGE);
        }
    }

    private void pickImage(){
        Intent intent5 = new Intent();
        intent5.setType("image/*");
        intent5.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent5, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_STORAGE && requestCode == Activity.RESULT_OK) {
            pickImage();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            // Get the cursor
            Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();
            //Get the column index of MediaStore.Images.Media.DATA
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //Gets the String value in the column
            String imgDecodableString = cursor.getString(columnIndex);
            cursor.close();
            // Set the Image in ImageView after decoding the String
            mImageAvata.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

        }
    }
}


