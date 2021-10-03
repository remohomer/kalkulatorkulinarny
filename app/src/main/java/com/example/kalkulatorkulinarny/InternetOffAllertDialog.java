package com.example.kalkulator;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class InternetOffAllertDialog {


    public InternetOffAllertDialog(Fragment fragment,
                                   InternetStatus internetStatus) {

        if (Build.VERSION.SDK_INT <= 23) {
            Toast.makeText(fragment.getActivity(), "Brak połączenia z internetem uniemożliwia poprawne wczytanie tej strony",
                    Toast.LENGTH_LONG).show();
        } else {

            Dialog dialog = new Dialog(fragment.getActivity());
            dialog.setContentView(R.layout.allert_dialog);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations =
                    android.R.style.Animation_Dialog;

            Button btTryAgain = dialog.findViewById(R.id.bt_try_again);
            btTryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.getActivity().recreate();
                }
            });
            Button btConnect = dialog.findViewById(R.id.bt_connect_to);
            btConnect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            });
            Button btOffline = dialog.findViewById(R.id.bt_offline);
            btOffline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    internetStatus.setOfflineMode(true);
                    fragment.getActivity().recreate();
                }
            });

            dialog.show();
        }
    }
}
