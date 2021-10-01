package com.example.lesiogotuje.ui.qr;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.lesiogotuje.InternetOffAllertDialog;
import com.example.lesiogotuje.InternetStatus;
import com.example.lesiogotuje.LoadWebsite;
import com.example.lesiogotuje.OfflineMode;
import com.example.lesiogotuje.R;
import com.example.lesiogotuje.databinding.FragmentScannerBinding;
import com.example.lesiogotuje.ui.update.CheckVersion;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


public class ScannerFragment extends Fragment {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 1;

    private FragmentScannerBinding binding;
    private CodeScanner mCodeScanner;

    private ConstraintLayout clResult;
    private ConstraintLayout clCameraPermission;

    private ImageView ivTitleIcon;
    private ImageView ivCopyIcon;
    private ImageView ivShareIcon;

    private TextView tvTip;
    private TextView tvTittle;
    private TextView tvResult;
    private TextView tvCopy;
    private TextView tvShare;

    private Button btCameleon;
    private Button btScanNext;
    Button btPermission;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentScannerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ivTitleIcon = binding.ivTitleIcon;
        ivCopyIcon = binding.ivCopyIcon;
        ivShareIcon = binding.ivShareIcon;

        tvTip = binding.tvTip;
        tvTittle = binding.tvTittle;
        tvResult = binding.tvResult;
        tvCopy = binding.tvCopy;
        tvShare = binding.tvShare;

        btCameleon = binding.btCameleon;
        btScanNext = binding.btScanNext;
        btScanNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clResult.setVisibility(View.GONE);
                mCodeScanner.startPreview();
            }
        });


        final Activity activity = getActivity();
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        clResult.setVisibility(View.VISIBLE);
                        ScannerStringInterpreter qrStringInterpreter = new ScannerStringInterpreter(result.toString());

                        if (qrStringInterpreter.isHttp() || qrStringInterpreter.isHttps()) {
                            // is website
                            if (qrStringInterpreter.isLGShortRecipe() || qrStringInterpreter.isLGLongRecipe()) {
                                // is lesiogotuje.pl
                                ivTitleIcon.setImageResource(R.drawable.logo_lesiogotuje_white);
                                tvTittle.setText(qrStringInterpreter.formatRecipe());

                                btCameleon.setText("Sprawdź ten przepis");
                                btCameleon.setVisibility(View.VISIBLE);
                                btCameleon.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ConstraintLayout clResult = binding.clWebview;
                                        final String websiteUrl = "" + result;
                                        final WebView webViewLG = binding.idWvLg;
                                        final ProgressBar progressBar = binding.idPbLoading;
                                        final TextView tvOffline = binding.idTvOffline;
                                        final TextView tvExplanation = binding.idTvExplanation;
                                        final Button btOnline = binding.btOnline;
                                        final Button btBackToScanner = binding.btBackToScanner;
                                        btBackToScanner.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                clResult.setVisibility(View.GONE);
                                            }
                                        });
                                        clResult.setVisibility(View.VISIBLE);

                                        InternetStatus internetStatus = new InternetStatus(getActivity());
                                        if (InternetStatus.isOfflineMode()) {
                                            OfflineMode offlineMode = new OfflineMode(getActivity(),
                                                    internetStatus,
                                                    tvOffline,
                                                    tvExplanation,
                                                    btOnline,
                                                    webViewLG,
                                                    progressBar);
                                        } else if (internetStatus.getActiveInternetConnection()) {
                                            LoadWebsite loadWebsite = new LoadWebsite(getActivity(),
                                                    webViewLG,
                                                    progressBar,
                                                    websiteUrl);
                                        } else {
                                            InternetOffAllertDialog internetOffAllertDialog = new InternetOffAllertDialog(getParentFragment(), internetStatus);
                                        }
                                    }
                                });
                                tvResult.setText(result.getText());
                            } else {
                                // is other website
                                ivTitleIcon.setImageResource(R.drawable.ic_public);
                                tvTittle.setText("Strona internetowa");

                                btCameleon.setText("Otwórz link");
                                btCameleon.setVisibility(View.VISIBLE);
                                btCameleon.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        goToLink(result.getText());
                                    }
                                });
                                tvResult.setText(result.getText());
                            }
                        } else if (qrStringInterpreter.isEmail()) {
                            // is Email Adress
                            ivTitleIcon.setImageResource(R.drawable.ic_email);
                            tvTittle.setText("Adres email");

                            btCameleon.setText("Wyślij wiadomość email");
                            btCameleon.setVisibility(View.VISIBLE);
                            btCameleon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sendToEmail(result.toString());
                                }
                            });
                            tvResult.setText(qrStringInterpreter.formatEmail());

                        } else if (qrStringInterpreter.isPhoneNumber()) {
                            // is Phone Number
                            ivTitleIcon.setImageResource(R.drawable.ic_phone);
                            tvTittle.setText("Numer telefonu");

                            btCameleon.setText("Zadzwoń na ten numer");
                            btCameleon.setVisibility(View.VISIBLE);
                            btCameleon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    callToPhone(qrStringInterpreter.formatPhone());
                                }
                            });
                            tvResult.setText(qrStringInterpreter.formatPhone());

                        } else if (qrStringInterpreter.isEncrypted()) {
                            // is Encrypted
                            ivTitleIcon.setImageResource(R.drawable.ic_lock);
                            tvTittle.setText("Treść zaszyfrowana");

                            btCameleon.setText("Odszyfruj");
                            btCameleon.setVisibility(View.VISIBLE);
                            btCameleon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });
                            tvResult.setText(result.getText());
                        } else if (qrStringInterpreter.isWifi()) {
                            // is Wifi
                            ivTitleIcon.setImageResource(R.drawable.ic_lock);
                            tvTittle.setText("Wifi");

                            btCameleon.setText("Połącz z siecią");
                            btCameleon.setVisibility(View.VISIBLE);
                            btCameleon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            });
                            tvResult.setText(result.getText());
                        } else {
                            // is everything else
                            ivTitleIcon.setImageResource(R.drawable.ic_text_snippet);
                            tvTittle.setText("Zwykły tekst");

                            btCameleon.setVisibility(View.GONE);
                            tvResult.setText(result.getText());
                        }

                        ivCopyIcon.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(getContext().CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Skopiowany tekst", result.getText());
                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(activity, "Skopiowane do schowka", Toast.LENGTH_SHORT).show();
                            }
                        });
                        tvCopy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(getContext().CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("Skopiowany tekst", result.getText());
                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(activity, "Skopiowane do schowka", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            mCodeScanner.startPreview();
        } else {
            clResult = binding.clResult;
            clCameraPermission = binding.clCameraPermission;
            clCameraPermission.setVisibility(View.VISIBLE);

            btPermission = binding.btPermission;
            btPermission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestForCamera();
                }
            });
        }

        return root;
    }


    private void requestForCamera() {

        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            goToApplicationSettings();
        } else {

            Dexter.withContext(getActivity()).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
                @Override
                public void onPermissionGranted(PermissionGrantedResponse response) {
                    Toast.makeText(getContext(), Html.fromHtml("Zezwolenie udzielone"), Toast.LENGTH_LONG).show();
                    clCameraPermission.setVisibility(View.GONE);
                    mCodeScanner.startPreview();
                }

                @Override
                public void onPermissionDenied(PermissionDeniedResponse response) {
                    Toast.makeText(getContext(), Html.fromHtml("Odmowa zezwolenia"), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }).check();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
//        requestForCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCodeScanner.releaseResources();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void goToLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void callToPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    private void sendToEmail(final String emailAdress) {
        startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + emailAdress)));
    }

    private void goToApplicationSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }
}
