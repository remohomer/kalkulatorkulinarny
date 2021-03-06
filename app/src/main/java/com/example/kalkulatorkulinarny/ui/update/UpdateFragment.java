package com.example.kalkulator.ui.update;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.kalkulator.InternetOffAllertDialog;
import com.example.kalkulator.InternetStatus;
import com.example.kalkulator.OfflineMode;
import com.example.kalkulator.R;
import com.example.kalkulator.ShowHideActionBar;
import com.example.kalkulator.databinding.FragmentUpdateBinding;

public class UpdateFragment extends Fragment {

    private FragmentUpdateBinding binding;

    private TextView textViewVersionAllert;
    private Button buttonDownload;

    private ImageView imageViewAllert;

    private String current = null;
    private String last = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        ShowHideActionBar.show(getActivity());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView tvOffline = binding.idTvOffline;
        TextView tvExplanation = binding.idTvExplanation;
        Button btDownload = binding.downloadButton;

        InternetStatus internetStatus = new InternetStatus(getActivity());
        if (InternetStatus.isOfflineMode()) {
            OfflineMode offlineMode = new OfflineMode(getActivity(),
                    internetStatus,
                    tvOffline,
                    tvExplanation,
                    btDownload);
        } else if (!internetStatus.getActiveInternetConnection()) {
            InternetOffAllertDialog internetOffAllertDialog = new InternetOffAllertDialog(this, internetStatus);
        } else {

            try {
                PackageManager manager = getContext().getPackageManager();
                PackageInfo info = manager.getPackageInfo(
                        getContext().getPackageName(), 0);
                current = info.versionName;
            } catch (Exception e) {
            }

            CheckVersion checkVersion = new CheckVersion(getActivity());
            last = checkVersion.getLastVersion();

            textViewVersionAllert = binding.versionAllert;
            textViewVersionAllert.setVisibility(View.VISIBLE);
            imageViewAllert = binding.ivVersionAllert;
            imageViewAllert.setVisibility(View.VISIBLE);
            buttonDownload = binding.downloadButton;

            if (last.equals("error") || last.equals("<!doctype html>")) {

                textViewVersionAllert.setText("Brak po????czenia z serwerem! \nSpr??buj p????niej...");
                imageViewAllert.setImageResource(R.drawable.ic_version_allert_maybe);
                buttonDownload.setVisibility(View.GONE);

            } else if (current != null && !last.equals(current)) {
                textViewVersionAllert.setText("Posiadasz nieaktualn?? wersj?? aplikacji!");

                imageViewAllert.setImageResource(R.drawable.ic_version_alert_bad);
                buttonDownload.setVisibility(View.VISIBLE);
                buttonDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToLink("https://lesiogotuje.pl/app/kalkulator-" + last + ".apk");
                    }
                });
            } else {
                textViewVersionAllert.setText("Posiadasz aktualn?? wersj?? aplikacji!");
                buttonDownload.setVisibility(View.GONE);
                imageViewAllert.setImageResource(R.drawable.ic_version_alert_good);
            }
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void goToLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

}