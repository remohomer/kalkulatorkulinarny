package com.example.lesiogotuje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

public class LauncherActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);

        Typeface customFont = Typeface.createFromAsset(getAssets(),"font/Oswald-Regular.ttf");
        textView = (TextView) findViewById(R.id.idTVAppName);
        textView.setTypeface(customFont);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LauncherActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },1500);
    }
}