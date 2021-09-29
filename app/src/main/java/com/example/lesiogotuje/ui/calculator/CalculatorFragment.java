package com.example.lesiogotuje.ui.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lesiogotuje.ShowHideActionBar;
import com.example.lesiogotuje.databinding.FragmentCalculatorBinding;
import com.google.android.material.tabs.TabLayout;


public class CalculatorFragment extends Fragment {


    private FragmentCalculatorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ShowHideActionBar.show(getActivity());

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getChildFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}