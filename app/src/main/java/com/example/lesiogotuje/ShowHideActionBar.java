package com.example.lesiogotuje;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class ShowHideActionBar {

    private Activity activity;

    public ShowHideActionBar() {
    }

    public static void show(Activity activity) {
        if (!((AppCompatActivity) activity).getSupportActionBar().isShowing()) {
            ((AppCompatActivity) activity).getSupportActionBar().show();
        }
    }

    public static void hide(Activity activity) {
        if (((AppCompatActivity) activity).getSupportActionBar().isShowing()) {
            ((AppCompatActivity) activity).getSupportActionBar().hide();
        }
    }
}
