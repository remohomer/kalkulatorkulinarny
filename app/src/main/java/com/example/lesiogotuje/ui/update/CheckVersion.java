package com.example.lesiogotuje.ui.update;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckVersion {

    private String lastVersion;

    public CheckVersion(Activity activity) {


        URL oracle = null;
        BufferedReader in = null;
        String inputLine;
        lastVersion = null;

        try {
            try {
                oracle = new URL("https://lesiogotuje.pl/app/version.txt");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                in = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                lastVersion = in.readLine();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            lastVersion = "error";
        }
    }

    public String getLastVersion() {
        return lastVersion;
    }
}

