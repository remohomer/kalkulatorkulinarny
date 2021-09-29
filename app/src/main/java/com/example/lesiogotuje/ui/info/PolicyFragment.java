package com.example.lesiogotuje.ui.info;

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
import com.example.lesiogotuje.databinding.FragmentPolicyBinding;



public class PolicyFragment extends Fragment {


    private FragmentPolicyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ShowHideActionBar.show(getActivity());

        binding = FragmentPolicyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView homeWebView = binding.idWebViewHome;
        final ProgressBar progressBar = binding.idPBLoading;
        final TextView tvOffline = binding.idTvOffline;
        final TextView tvExplanation = binding.idTvExplanation;
        final Button btOnline = binding.btOnline;

        final String websiteUrl = "https://lesiogotuje.pl/um-polityka-prywatnosci/";

        InternetStatus internetStatus = new InternetStatus(getActivity());
        if (internetStatus.isOfflineMode()) {
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