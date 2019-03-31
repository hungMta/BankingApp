package com.hungtran.bankingassistant.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class ImageHelper {

    public static Bitmap resizeMapIcons(Context context, String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(context.getResources(),
                context.getResources().getIdentifier(iconName, "drawable", context.getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    public static BitmapDescriptor getMyPlaceIcon(Context context) {
        return BitmapDescriptorFactory.fromBitmap(resizeMapIcons(context, "icon_my_place",
                60, 100));
    }

    public static BitmapDescriptor getBranchPlaceIcon(Context context) {
        return BitmapDescriptorFactory.fromBitmap(resizeMapIcons(context, "icon_home_place",
                60, 100));
    }

    public static BitmapDescriptor getAtmPlaceIcon(Context context) {
        return BitmapDescriptorFactory.fromBitmap(resizeMapIcons(context, "icon_atm_place",
                60, 100));
    }

}
