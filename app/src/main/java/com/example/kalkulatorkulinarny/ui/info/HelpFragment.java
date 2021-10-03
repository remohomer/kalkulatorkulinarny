package com.example.kalkulator.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.kalkulator.InternetOffAllertDialog;
import com.example.kalkulator.InternetStatus;
import com.example.kalkulator.LoadWebsite;
import com.example.kalkulator.OfflineMode;
import com.example.kalkulator.ShowHideActionBar;
import com.example.kalkulator.databinding.FragmentHelpBinding;



public class HelpFragment extends Fragment {


    private FragmentHelpBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ShowHideActionBar.show(getActivity());

        binding = FragmentHelpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView homeWebView = binding.idWebViewHome;
        final ProgressBar progressBar = binding.idPBLoading;
        final TextView tvOffline = binding.idTvOffline;
        final TextView tvExplanation = binding.idTvExplanation;
        final Button btOnline = binding.btOnline;

        final String websiteUrl = "https://lesiogotuje.pl/pomoc-kalkulator/";

        InternetStatus internetStatus = new InternetStatus(getActivity());
        if (InternetStatus.isOfflineMode()) {
            OfflineMode offlineMode = new OfflineMode(getActivity(),
                    internetStatus,
                    tvOffline,
                    tvExplanation,
                    btOnline,
                    homeWebView,
                    progressBar);
        } else if (!internetStatus.getActiveInternetConnection()) {
            InternetOffAllertDialog internetOffAllertDialog = new InternetOffAllertDialog(this, internetStatus);
        } else {
            LoadWebsite loadWebsite = new LoadWebsite(getActivity(),
                    homeWebView,
                    progressBar,
                    websiteUrl);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
