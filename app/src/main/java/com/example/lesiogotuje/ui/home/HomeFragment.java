package com.example.lesiogotuje.ui.home;

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

import com.example.lesiogotuje.InternetOffAllertDialog;
import com.example.lesiogotuje.InternetStatus;
import com.example.lesiogotuje.LoadWebsite;
import com.example.lesiogotuje.OfflineMode;
import com.example.lesiogotuje.ShowHideActionBar;
import com.example.lesiogotuje.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ShowHideActionBar.hide(getActivity());

        final WebView homeWebView = binding.idWebViewHome;
        final ProgressBar progressBar = binding.idPBLoading;
        final TextView tvOffline = binding.idTvOffline;
        final TextView tvExplanation = binding.idTvExplanation;
        final Button btOnline = binding.btOnline;

        final String websiteUrl = "https://lesiogotuje.pl/start-app/";

        InternetStatus internetStatus = new InternetStatus(getActivity());
        if (internetStatus.isOfflineMode()) {
            OfflineMode offlineMode = new OfflineMode(getActivity(),
                    internetStatus,
                    tvOffline,
                    tvExplanation,
                    btOnline,
                    homeWebView,
                    progressBar);
        } else if (internetStatus.getActiveInternetConnection()) {
            LoadWebsite loadWebsite = new LoadWebsite(getActivity(),
                    homeWebView,
                    progressBar,
                    websiteUrl);
        } else {
            InternetOffAllertDialog internetOffAllertDialog = new InternetOffAllertDialog(this, internetStatus);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}