package com.fahmy.testproject.utils;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

import com.fahmy.testproject.R;

public class CommonUtils {

    public static void hideProgressBar(Activity context) {
        context.findViewById(R.id.progress_bar).setVisibility(View.GONE);
        context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void showProgressBar(Activity context) {
        context.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
