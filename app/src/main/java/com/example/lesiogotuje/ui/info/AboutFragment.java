package com.example.lesiogotuje.ui.info;

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

import com.example.lesiogotuje.ShowHideActionBar;
import com.example.lesiogotuje.databinding.FragmentAboutBinding;



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
                "<p><strong>Nazwa:</strong> Lesio Gotuje" +
                "<br>" +
                "<strong>Aktualna wersja:</strong> " + currentVersionOfApp +
                "<br>" +
                "<strong>Wymagany Android:</strong> 4.4 i nowsze" +
                "<br>" +
                "<strong>Zaktualizowano:</strong> 29.09.2021" +
                "<br>" +
                "<strong>Autor:</strong> Adam Wojdan</p>" +
                "<br>" +
                "<br>" +
                "<h2>OPIS</h2>" +
                "<br>" +
                "<p>Oficjalna aplikacja strony LesioGotuje.pl wydana na system operacyjny Android. Ułatwia przeglądanie treści witryny bez konieczności korzystania z przeglądarki internetowej.\n" +
                "Aplikacja posiada wbudowany kalkulator kulinarny i przelicznik Blg oraz wczytuje nasze strony znacznie szybciej niż przeglądarka internetowa.</p>" +
                "<br>" +
                "<br>" +
                "<h2>ZEZWOLENIA</h2>" +
                "<br>" +
                "<p>Funkcja Skaner kodów QR wymaga od użytkownika zgody na dostęp do kamery podczas korzystania z aplikacji. Nie wyrażenie zgody skutkuje brakiem możliwości skanowania kodów QR. " +
                "<br>" +
                "Wszystkie pozostałe funkcję aplikacji nie wymagają dodatkowych zezwoleń od użytkownika.</p>" +
                "<br>" +
                "<br>" +
                "<h2>POLITYKA PRYWATNOŚCI</h2>" +
                "<br>" +
                "<p>Podczas korzystania z naszej aplikacji obowiązuje Polityka Prywatności oraz Zasady i Warunki strony LesioGotuje.pl.</p>" +
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