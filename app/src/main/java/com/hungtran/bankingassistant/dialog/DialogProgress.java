package com.hungtran.bankingassistant.dialog;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import com.hungtran.bankingassistant.R;

public class DialogProgress extends DialogFragment {

    public static DialogProgress newInstance(){
        return  new DialogProgress();
    }
    private ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        View view = inflater.inflate(R.layout.fragment_dialog_progress, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        progressBar = view.findViewById(R.id.progressbar);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(getContext(), R.color.colorPrimary));
            progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            progressBar.setProgressTintList(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorPrimary)));
        }
        return view;
    }
}
