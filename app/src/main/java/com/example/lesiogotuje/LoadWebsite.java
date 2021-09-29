package com.example.lesiogotuje;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.InputStream;

public class LoadWebsite {


    public LoadWebsite(Activity activity,
                       WebView webView,
                       ProgressBar progressBar,
                       String websiteUrl) {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(websiteUrl);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                injectCSS(activity, webView);
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                            }
                    }
                }
                return false;
            }
        });
    }

    public void hideSomeSectionOfWebsite(WebView webView) {
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('site-header')[0].style.display='none';" +
                "document.getElementsByClassName('site-footer')[0].style.display='none'; })()");

    }

    private void injectCSS(Activity activity, WebView webView) {
        try {

            InputStream inputStream = activity.getAssets().open("hide.css");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            webView.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
