package com.example.lesiogotuje;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lesiogotuje.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavigationView bottomNavigationView;
    private AppBarConfiguration bottomAppBarConfiguration;
    private long pressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_calculator, R.id.navigation_qr, R.id.navigation_info, R.id.navigation_update)
                .build();
        NavController bottomNavController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, bottomNavController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, bottomNavController);

    }

    @Override
    public void onBackPressed() {
        if(pressTime+1500>System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        }
        pressTime = System.currentTimeMillis();
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        closeKeyBoard(this);
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        return NavigationUI.navigateUp(navController, bottomAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    public static void closeKeyBoard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    public static boolean checkInternetConnection(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                activity.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }
}