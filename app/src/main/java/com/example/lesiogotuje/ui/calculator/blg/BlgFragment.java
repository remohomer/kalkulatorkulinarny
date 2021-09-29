package com.example.lesiogotuje.ui.calculator.blg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lesiogotuje.databinding.FragmentCalculatorBlgBinding;

public class BlgFragment extends Fragment {

    private FragmentCalculatorBlgBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalculatorBlgBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        TextView resultsTextView = binding.textViewWineType;
        SeekBar seekBar = binding.seekBarBlg;
        TextView currentBlg = binding.textViewCurrent;
        TextView textWine = binding.textViewWine;
        TextView textTipHeader = binding.textViewTip;
        TextView textWineType = binding.textViewWineType;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                textTipHeader.setVisibility(View.INVISIBLE);
                textWineType.setVisibility(View.VISIBLE);

                double blg = seekBar.getProgress() / 2.0;

                if (seekBar.getProgress() < 16) {
                    String resultString = blg + " Blg";
                    currentBlg.setText(resultString);
                } else {
                    String resultString = "8+ Blg";
                    currentBlg.setText(resultString);
                }

                if (textWine.getText().equals("Witaj!")) {
                    String resultString = "Wino";
                    textWine.setText(resultString);
                }

                if (seekBar.getProgress() <= 3) {
                    String resultTextViewString = "Wytrawne";
                    resultsTextView.setTextColor(0xFF673AB7);
                    resultsTextView.setText(resultTextViewString);
                } else if (seekBar.getProgress() <= 8) {
                    String resultTextViewString = "Półwytrawne";
                    resultsTextView.setTextColor(0xFF9C27B0);
                    resultsTextView.setText(resultTextViewString);
                } else if (seekBar.getProgress() <= 15) {
                    String resultTextViewString = "Półsłodkie";
                    resultsTextView.setTextColor(0xFFE91E63);
                    resultsTextView.setText(resultTextViewString);
                } else {
                    String resultTextViewString = "Słodkie";
                    resultsTextView.setTextColor(0xFFF44336);
                    resultsTextView.setText(resultTextViewString);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}