package com.example.lesiogotuje.ui.qr;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lesiogotuje.R;
import com.example.lesiogotuje.ShowHideActionBar;
import com.example.lesiogotuje.databinding.FragmentGeneratorBinding;
import com.example.lesiogotuje.ui.calculator.dilute.Action;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class GeneratorFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private FragmentGeneratorBinding binding;

    private StringType stringType;

    private Spinner spTextType;
    private EditText etInput;
    private Button btGenerate;
    private ImageView ivOutput;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ShowHideActionBar.show(getActivity());

        binding = FragmentGeneratorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spTextType = binding.spTextType;
        etInput = binding.etInput;
        btGenerate = binding.btGenerate;
        ivOutput = binding.ivOutput;

        Spinner spTextType = binding.spTextType;
        spTextType.setEnabled(true);
        spTextType.setClickable(true);
        ArrayAdapter<CharSequence> arrayAdapterIngredient = ArrayAdapter.createFromResource(getActivity(), R.array.generator_qr, R.layout.support_simple_spinner_dropdown_item);
        arrayAdapterIngredient.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spTextType.setAdapter(arrayAdapterIngredient);
        spTextType.setOnItemSelectedListener(this);



        spTextType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (spTextType.getSelectedItem().toString()) {

                    case "Zwykły tekst": {
                        stringType = StringType.TEXT;
                        break;
                    }
                    case "Strona internetowa": {
                        stringType = StringType.WEBSITE;
                        break;
                    }
                    case "Email": {
                        stringType = StringType.EMAIL;
                        break;
                    }
                    case "Telefon": {
                        stringType = StringType.PHONE;
                        break;
                    }
                    case "Kontakt": {
                        stringType = StringType.CONTACT;
                        break;
                    }
                    case "SMS": {
                        stringType = StringType.SMS;
                        break;
                    }
                    case "Wydarzenie": {
                        stringType = StringType.EVENT;
                        break;
                    }
                    case "Lokalizacja": {
                        stringType = StringType.GPS;
                        break;
                    }
                    case "WIFI": {
                        stringType = StringType.WIFI;
                        break;
                    }
                    default: {
                        stringType = StringType.TEXT;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input value from edit text
                String qrCodeInputString;
                if (etInput.getText().toString().trim().equals("")) {
                    Toast.makeText(getActivity(), "Uzupełnij wszystkie pola!",
                            Toast.LENGTH_LONG).show();
                } else {
                    qrCodeInputString = etInput.getText().toString().trim();


                    GeneratorStringMaker generatorStringMaker = new GeneratorStringMaker(qrCodeInputString, stringType);
                    String sText = generatorStringMaker.getStringResult();



                    // Initlialize multi format writer
                    MultiFormatWriter writer = new MultiFormatWriter();

                    try {
                        // Initlialize but matrix
                        BitMatrix matrix = writer.encode(sText,
                                BarcodeFormat.QR_CODE,
                                350,
                                350);
                        // Initlialize barcode encoder
                        BarcodeEncoder encoder = new BarcodeEncoder();

                        // Initlialize bitmap
                        Bitmap bitmap = encoder.createBitmap(matrix);

                        // Set bitmap on image view
                        ivOutput.setImageBitmap(bitmap);

                        // Initlialize input manager
                        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(
                                Context.INPUT_METHOD_SERVICE
                        );

                        // Hide soft keyboard
                        manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(), 0);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}