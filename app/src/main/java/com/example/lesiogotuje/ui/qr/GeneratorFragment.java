package com.example.lesiogotuje.ui.qr;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.Html;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.lesiogotuje.R;
import com.example.lesiogotuje.ShowHideActionBar;
import com.example.lesiogotuje.databinding.FragmentGeneratorBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class GeneratorFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    private FragmentGeneratorBinding binding;

    private StringType stringType;

    private Spinner spTextType;
    private EditText etInput;
    private Button btGenerate;
    private ImageView ivOutput;
    private Button btSave;

    private Bitmap bitmap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ShowHideActionBar.show(getActivity());

        binding = FragmentGeneratorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spTextType = binding.spTextType;
        etInput = binding.etInput;
        btGenerate = binding.btGenerate;
        ivOutput = binding.ivOutput;

        btSave = binding.btSave;

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
                            Toast.LENGTH_SHORT).show();
                } else {
                    qrCodeInputString = etInput.getText().toString().trim();

                    GeneratorStringMaker generatorStringMaker = new GeneratorStringMaker(qrCodeInputString, stringType);
                    String sText = generatorStringMaker.getStringResult();

                    // Initlialize multi format writer
                    MultiFormatWriter writer = new MultiFormatWriter();

                    try {
                        BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        bitmap = encoder.createBitmap(matrix);

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

                    btSave.setVisibility(View.VISIBLE);
                    btSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                saveImage(bitmap);
                            } else {
                                requestForExternalStorage();
                            }
                        }
                    });
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

    private void saveImage(Bitmap finalBitmap) {

        File myDir = commonDocumentDirPath("LesioGotuje - QR");
        myDir.mkdirs();
        String fname = "QR-" + etInput.getText().toString() + "-" + System.currentTimeMillis() + ".png";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            Toast.makeText(getContext(), "Obraz zapisany", Toast.LENGTH_SHORT).show();
            out.close();

        } catch (Exception e) {
            Toast.makeText(getContext(), "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static File commonDocumentDirPath(String FolderName) {
        File dir = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + FolderName);
        } else {
            dir = new File(Environment.getExternalStorageDirectory() + "/" + FolderName);
        }

        // Make sure the path directory exists.
        if (!dir.exists()) {
            // Make it, if it doesn't exit
            boolean success = dir.mkdirs();
            if (!success) {
                dir = null;
            }
        }
        return dir;
    }

    private void requestForExternalStorage() {

        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), Html.fromHtml("Wymagana jest zgoda na dostęp do pamięci"), Toast.LENGTH_LONG).show();
            goToApplicationSettings();
        } else {
            Dexter.withContext(getActivity()).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            Toast.makeText(getContext(), Html.fromHtml("Zezwolenie udzielone"), Toast.LENGTH_LONG).show();
                            saveImage(bitmap);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                            Toast.makeText(getContext(), Html.fromHtml("Odmowa zezwolenia"), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        }
    }

    private void goToApplicationSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

}