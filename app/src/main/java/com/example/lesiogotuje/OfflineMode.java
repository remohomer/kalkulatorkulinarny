package com.example.lesiogotuje;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class OfflineMode {

    public OfflineMode(Activity activity,
                       InternetStatus internetStatus,
                       TextView tvOffline,
                       TextView tvExplanation,
                       Button btOnline,
                       WebView webView,
                       ProgressBar progressBar) {

        webView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        tvOffline.setVisibility(View.VISIBLE);
        tvExplanation.setVisibility(View.VISIBLE);
        btOnline.setVisibility(View.VISIBLE);
        btOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internetStatus.setOfflineMode(false);
                activity.recreate();
            }
        });
        Typeface customFontRegular = Typeface.createFromAsset(activity.getAssets(), "font/Oswald-Regular.ttf");
        Typeface customFontMedium = Typeface.createFromAsset(activity.getAssets(), "font/Oswald-Medium.ttf");
        tvOffline.setTypeface(customFontRegular);
        tvExplanation.setTypeface(customFontMedium);
    }

    public OfflineMode(Activity activity,
                       InternetStatus internetStatus,
                       TextView tvOffline,
                       TextView tvExplanation,
                       Button btOnline) {

        btOnline.setText("Przełącz na online");

        tvOffline.setVisibility(View.VISIBLE);
        tvExplanation.setVisibility(View.VISIBLE);
        btOnline.setVisibility(View.VISIBLE);
        btOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                internetStatus.setOfflineMode(false);
                activity.recreate();
            }
        });
        Typeface customFontRegular = Typeface.createFromAsset(activity.getAssets(), "font/Oswald-Regular.ttf");
        Typeface customFontMedium = Typeface.createFromAsset(activity.getAssets(), "font/Oswald-Medium.ttf");
        tvOffline.setTypeface(customFontRegular);
        tvExplanation.setTypeface(customFontMedium);
    }

}
