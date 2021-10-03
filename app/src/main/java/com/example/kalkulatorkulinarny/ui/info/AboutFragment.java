package com.example.kalkulator.ui.info;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.kalkulator.ShowHideActionBar;
import com.example.kalkulator.databinding.FragmentAboutBinding;


public class AboutFragment extends Fragment {


    private FragmentAboutBinding binding;
    private TextView information;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ShowHideActionBar.show(getActivity());

        binding = FragmentAboutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        information = binding.idTvInfo;

        String currentVersionOfApp;

        try {
            PackageManager manager = getContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    getContext().getPackageName(), 0);
            currentVersionOfApp = info.versionName;
        } catch (Exception e) {
            currentVersionOfApp = "BŁĄD POBIERANIA DANYCH";
        }

        String source = "" +
                "<h2>INFORMACJE</h2>" +
                "<br>" +
                "<p><strong>Nazwa:</strong> Kalkulator kulinarny" +
                "<br>" +
                "<strong>Aktualna wersja:</strong> " + currentVersionOfApp +
                "<br>" +
                "<strong>Wymagany Android:</strong> 4.4 i nowsze" +
                "<br>" +
                "<strong>Zaktualizowano:</strong> 03.10.2021" +
                "<br>" +
                "<strong>Autor:</strong> Adam Wojdan</p>" +
                "<br>" +
                "<br>" +
                "<h2>OPIS</h2>" +
                "<br>" +
                "<p>Aplikacja do pomocy kuchennej z wbudowanymi kalkulatorami kulinarnymi oraz czytnikiem i generatorem kodów QR.</p>" +
                "<br>" +
                "<br>" +
                "<h2>ZEZWOLENIA</h2>" +
                "<br>" +
                "<p>Funkcja Skaner kodów QR wymaga od użytkownika zgody na dostęp do kamery podczas korzystania z aplikacji. Nie wyrażenie zgody skutkuje brakiem możliwości skanowania kodów QR.</p>" +
                "<br>" +
                "<p>Funkcja Generator kodów QR wymaga od użytkownika zgody na dostęp do pamięci podczas zapisywania obrazów z wygenerowanym kodem na urządzeniu.  Nie wyrażenie zgody skutkuje brakiem możliwości zapisu wygenerowanego kodu na urządzeniu.</p>" +
                "<br>" +
                "Wszystkie pozostałe funkcję aplikacji nie wymagają dodatkowych zezwoleń od użytkownika.</p>" +
                "<br>" +
                "<br>" +
                "<h2>PODZIĘKOWANIA</h2>" +
                "<br>" +
                "<p>Dziękujemy Ci za pobranie naszej aplikacji!!</p>" +
                "";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            information.setText(Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT));
        } else {
            information.setText(Html.fromHtml(source));
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}