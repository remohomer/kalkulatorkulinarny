package com.example.lesiogotuje.ui.calculator.culinary;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.lesiogotuje.LoadWebsite;
import com.example.lesiogotuje.MainActivity;
import com.example.lesiogotuje.R;
import com.example.lesiogotuje.databinding.FragmentCalculatorCulinaryBinding;

public class CulinaryFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentCalculatorCulinaryBinding binding;

    private Spinner ingredientSpinner;
    private Spinner sourceUnitSpinner;
    private Spinner destinyUnitSpinner;
    private EditText resultValueEditText;

    private Ingredient BreadCrumbs = new Ingredient("Bułka tarta", 0.44);
    private Ingredient Sugar = new Ingredient("Cukier biały", 0.8);
    private Ingredient BrownSugar = new Ingredient("Cukier brązowy", 0.6);
    private Ingredient IcingSugar = new Ingredient("Cukier puder", 0.48);
    private Ingredient VanillaSugar = new Ingredient("Cukier wanilinowy", 0.768);
    private Ingredient Cocoa = new Ingredient("Kakao", 0.64);
    private Ingredient Semolina = new Ingredient("Kasza manna", 0.68);
    private Ingredient Ketchup = new Ingredient("Keczup", 0.64);
    private Ingredient Kefir = new Ingredient("Kefir", 0.96);
    private Ingredient Mayonnaise = new Ingredient("Majonez", 1.6);
    private Ingredient Margarine = new Ingredient("Margaryna", 1.28);
    private Ingredient Butter = new Ingredient("Masło", 1.28);
    private Ingredient Buttermilk = new Ingredient("Maślanka", 0.96);
    private Ingredient WheatFlour500 = new Ingredient("Mąka pszenna 500", 0.52);
    private Ingredient WheatFlour750 = new Ingredient("Mąka pszenna 750", 0.6);
    private Ingredient RyeFlour720 = new Ingredient("Mąka żytnia 720", 0.46);
    private Ingredient RyeFlour2000 = new Ingredient("Mąka żytnia 2000", 0.48);
    private Ingredient Honey = new Ingredient("Miód", 1.32);
    private Ingredient Milk = new Ingredient("Mleko", 0.92);
    private Ingredient Mustard = new Ingredient("Musztarda", 1.28);
    private Ingredient Oil = new Ingredient("Olej", 0.86);
    private Ingredient OliveOil = new Ingredient("Oliwa", 0.64);
    private Ingredient CornFlakes = new Ingredient("Płatki kukurydziane", 0.208);
    private Ingredient OatFlakes = new Ingredient("Płatki owsiane", 0.64);
    private Ingredient Pepper = new Ingredient("Pieprz mielony", 0.2);
    private Ingredient WhiteRise = new Ingredient("Ryż biały", 0.76);
    private Ingredient BrownRise = new Ingredient("Ryż brązowy", 0.728);
    private Ingredient Salt = new Ingredient("Sól kuchenna", 1.152);
    private Ingredient RockSalt = new Ingredient("Sól gruboziarnista", 1.536);
    private Ingredient Alcohol = new Ingredient("Spirytus", 0.66);
    private Ingredient Cream12 = new Ingredient("Śmietana 12%", 0.512);
    private Ingredient Cream18 = new Ingredient("Śmietana 18%", 0.625);
    private Ingredient Cream30 = new Ingredient("Śmietana 30%", 0.64);
    private Ingredient Water = new Ingredient("Woda", 1);
    private Ingredient Wine = new Ingredient("Wino", 0.98);
    private Ingredient Vodka = new Ingredient("Wódka", 0.928);
    private Ingredient Empty = new Ingredient("Brak", -1);

    private ArrayAdapter<CharSequence> arrayAdapterSourceUnit;
    private ArrayAdapter<CharSequence> arrayAdapterDestinySpinner;

    private SwitchCompat swEngUnits;
    private SwitchCompat swPlUnits;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalculatorCulinaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String source = "" +
                "<h3>JEDNOSTKI KULINARNE</h3>" +
                "<br>" +
                "<p>" +
                "1 " + Unit.PINCH.getSingularName() + " = 0,5g" + "<br>" +
                "1 " + Unit.TEASPOON.getSingularName() + " = 5g" + "<br>" +
                "1 " + Unit.SPOON.getSingularName() + " = 15g" + "<br>" +
                "1 " + Unit.GLASS.getSingularName() + " = 250g" + "<br>" +
                "</p>" +
                "<br>" +
                "<h3>POLSKIE</h3>" +
                "<br>" +
                "<p>" +
                "1 <strong>" + Unit.GRAM.getSingularName() + "</strong>(g) = <strong>" + Unit.MILILITR.getRoundedValue() + "ml</strong> (wody)" + "<br>" +
                "1 <strong>" + Unit.DEKAGRAM.getSingularName() + "</strong>(dag) = <strong>"+Unit.DEKAGRAM.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + Unit.KILOGRAM.getSingularName() + "</strong>(kg) = <strong>"+Unit.KILOGRAM.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + Unit.MILILITR.getSingularName() + "</strong>(ml) = <strong>"+Unit.GRAM.getRoundedValue()+"g</strong> (wody)" + "<br>" +
                "1 <strong>" + Unit.LITR.getSingularName() + "</strong>(l) = <strong>"+Unit.LITR.getRoundedValue()+"ml</strong>" + "<br>" +
                "</p>" +
                "<br>" +
                "<h3>ANGIELSKIE</h3>" +
                "<br>" +
                "<p>" +
                "1 <strong>" + Unit.GR.getSingularName() + "</strong>(gr) = <strong>"+Unit.GR.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + Unit.DR.getSingularName() + "</strong>(dr) = <strong>"+Unit.DR.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + Unit.OZ.getSingularName() + "</strong>(oz) = <strong>"+Unit.OZ.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + Unit.LB.getSingularName() + "</strong>(lb) = <strong>"+Unit.LB.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + Unit.OZF.getSingularName() + "</strong>(oz(fl)) = <strong>"+Unit.OZF.getRoundedValue()+"ml</strong>" + "<br>" +
                "1 <strong>" + Unit.PT.getSingularName() + "</strong>(pt) = <strong>"+Unit.PT.getRoundedValue()+"ml</strong>" + "<br>" +
                "1 <strong>" + Unit.QT.getSingularName() + "</strong>(qt) = <strong>"+Unit.QT.getRoundedValue()+"ml</strong>" + "<br>" +
                "1 <strong>" + Unit.GAL.getSingularName() + "</strong>(gal) = <strong>"+Unit.GAL.getRoundedValue()+"ml</strong>" + "<br>" +
                "</p>" +
                "<br>" +
                "<br>" +
                "<h2>STOSUNEK WAGI DO OBJĘTOŚCI</h2>" +
                "<br>" +
                "<p>" +
                BreadCrumbs.getName() + " = <strong>" + BreadCrumbs.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Sugar.getName() + " = <strong>" + Sugar.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                BrownSugar.getName() + " = <strong>" + BrownSugar.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                IcingSugar.getName() + " = <strong>" + IcingSugar.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                VanillaSugar.getName() + " = <strong>" + VanillaSugar.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Cocoa.getName() + " = <strong>" + Cocoa.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Semolina.getName() + " = <strong>" + Semolina.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Ketchup.getName() + " = <strong>" + Ketchup.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Kefir.getName() + " = <strong>" + Kefir.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Mayonnaise.getName() + " = <strong>" + Mayonnaise.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Margarine.getName() + " = <strong>" + Margarine.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Butter.getName() + " = <strong>" + Butter.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Buttermilk.getName() + " = <strong>" + Buttermilk.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                WheatFlour500.getName() + " = <strong>" + WheatFlour500.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                WheatFlour750.getName() + " = <strong>" + WheatFlour750.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                RyeFlour720.getName() + " = <strong>" + RyeFlour720.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                RyeFlour2000.getName() + " = <strong>" + RyeFlour2000.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Honey.getName() + " = <strong>" + Honey.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Milk.getName() + " = <strong>" + Milk.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Mustard.getName() + " = <strong>" + Mustard.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Oil.getName() + " = <strong>" + Oil.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                OliveOil.getName() + " = <strong>" + OliveOil.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                CornFlakes.getName() + " = <strong>" + CornFlakes.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                OatFlakes.getName() + " = <strong>" + OatFlakes.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Pepper.getName() + " = <strong>" + Pepper.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                WhiteRise.getName() + " = <strong>" + WhiteRise.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                BrownRise.getName() + " = <strong>" + BrownRise.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Salt.getName() + " = <strong>" + Salt.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                RockSalt.getName() + " = <strong>" + RockSalt.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Alcohol.getName() + " = <strong>" + Alcohol.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Cream12.getName() + " = <strong>" + Cream12.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Cream18.getName() + " = <strong>" + Cream18.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Cream30.getName() + " = <strong>" + Cream30.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Water.getName() + " = <strong>" + Water.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Wine.getName() + " = <strong>" + Wine.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                Vodka.getName() + " = <strong>" + Vodka.getWeightToVolumeRatio() + "</strong>" + "<br>" +
                "</p>" +
                "";

        ImageView iVMoreInfo;
        TextView tVLegend = binding.idTvLegend;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tVLegend.setText(Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tVLegend.setText(Html.fromHtml(source));
        }

        TextView tVMoreInfo = binding.tvMoreInfo;
        iVMoreInfo = binding.ivTvMoreInfo;
        LinearLayout lLMoreInfo = binding.llMoreInfo;
        lLMoreInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tVLegend.getVisibility() == View.GONE) {
                        tVLegend.setVisibility(View.VISIBLE);
                        tVMoreInfo.setText("Pokaż mniej");
                        iVMoreInfo.setImageResource(R.drawable.ic_arrow_up);
                    } else {
                        tVLegend.setVisibility(View.GONE);
                        tVMoreInfo.setText("Pokaż więcej");
                        iVMoreInfo.setImageResource(R.drawable.ic_arrow_down);
                    }
                }
        });

        EditText editTextValue = binding.EditTextValue;
        editTextValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //TODO
                    checkAllEditTextAndCalculate(ingredientSpinner,
                            sourceUnitSpinner,
                            destinyUnitSpinner,
                            editTextValue,
                            resultValueEditText);

                    handled = true;
                }
                return handled;
            }
        });

        ingredientSpinner = binding.spinnerIngredient;
        ArrayAdapter<CharSequence> arrayAdapterIngredient = ArrayAdapter.createFromResource(getActivity(), R.array.ingredients, R.layout.spinner_item_ingredient);
        arrayAdapterIngredient.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ingredientSpinner.setAdapter(arrayAdapterIngredient);
        ingredientSpinner.setOnItemSelectedListener(this);

        sourceUnitSpinner = binding.spinnerSourceUnit;
        arrayAdapterSourceUnit = ArrayAdapter.createFromResource(getActivity(), R.array.pl_units, R.layout.spinner_item_unit);
        arrayAdapterSourceUnit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(arrayAdapterSourceUnit);
        sourceUnitSpinner.setOnItemSelectedListener(this);

        destinyUnitSpinner = binding.spinnerDestinyUnit;
        arrayAdapterDestinySpinner = ArrayAdapter.createFromResource(getActivity(), R.array.pl_units, R.layout.spinner_item_unit);
        arrayAdapterDestinySpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        destinyUnitSpinner.setAdapter(arrayAdapterDestinySpinner);
        destinyUnitSpinner.setOnItemSelectedListener(this);


        swPlUnits = binding.swPlUnits;
        swPlUnits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton plButtonView, boolean isChecked) {
                if (plButtonView.isChecked()) {
                    if (swEngUnits.isChecked()) {
                        setPlEngUnits();
                    } else {
                        setPlUnits();
                    }

                } else {
                    if (swEngUnits.isChecked()) {
                        setEngUnits();
                    } else {
                        setCulinaryUnits();
                    }
                }
            }
        });


        swEngUnits = binding.swEngUnits;
        swEngUnits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton engButtonView, boolean isChecked) {

                if (engButtonView.isChecked()) {
                    if (swPlUnits.isChecked()) {
                        setPlEngUnits();
                    } else {
                        setEngUnits();
                    }

                } else {
                    if (swPlUnits.isChecked()) {
                        setPlUnits();
                    } else {
                        setCulinaryUnits();
                    }
                }
            }
        });

        resultValueEditText = binding.EditTextResultValue;

        Button buttonCount = binding.buttonCount;
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAllEditTextAndCalculate(ingredientSpinner,
                        sourceUnitSpinner,
                        destinyUnitSpinner,
                        editTextValue,
                        resultValueEditText);
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

    public void checkAllEditTextAndCalculate(Spinner ingredientSpinner,
                                             Spinner sourceUnitSpinner,
                                             Spinner destinyUnitSpinner,
                                             EditText editTextValue,
                                             EditText resultValueEditText) {


        MainActivity.closeKeyBoard(getActivity());
        Activity activity = new Activity();
        MainActivity.closeKeyBoard(activity);
        Unit selectedSourceUnit;
        Unit selectedDestinyUnit;
        Ingredient selectedIngredient;

        try {
            switch (ingredientSpinner.getSelectedItem().toString()) {
                case "Bułka tarta": {
                    selectedIngredient = BreadCrumbs;
                    break;
                }
                case "Cukier biały": {
                    selectedIngredient = Sugar;
                    break;
                }
                case "Cukier brązowy": {
                    selectedIngredient = BrownSugar;
                    break;
                }
                case "Cukier puder": {
                    selectedIngredient = IcingSugar;
                    break;
                }
                case "Cukier wanilinowy": {
                    selectedIngredient = VanillaSugar;
                    break;
                }
                case "Kakao": {
                    selectedIngredient = Cocoa;
                    break;
                }
                case "Kasza manna": {
                    selectedIngredient = Semolina;
                    break;
                }
                case "Keczup": {
                    selectedIngredient = Ketchup;
                    break;
                }
                case "Kefir": {
                    selectedIngredient = Kefir;
                    break;
                }
                case "Majonez": {
                    selectedIngredient = Mayonnaise;
                    break;
                }
                case "Margaryna": {
                    selectedIngredient = Margarine;
                    break;
                }
                case "Masło": {
                    selectedIngredient = Butter;
                    break;
                }
                case "Maślanka": {
                    selectedIngredient = Buttermilk;
                    break;
                }
                case "Mąka pszenna 500": {
                    selectedIngredient = WheatFlour500;
                    break;
                }
                case "Mąka pszenna 750": {
                    selectedIngredient = WheatFlour750;
                    break;
                }
                case "Mąka żytnia 720": {
                    selectedIngredient = RyeFlour720;
                    break;
                }
                case "Mąka żytnia 2000": {
                    selectedIngredient = RyeFlour2000;
                    break;
                }
                case "Miód": {
                    selectedIngredient = Honey;
                    break;
                }
                case "Mleko": {
                    selectedIngredient = Milk;
                    break;
                }
                case "Musztarda": {
                    selectedIngredient = Mustard;
                    break;
                }
                case "Olej": {
                    selectedIngredient = Oil;
                    break;
                }
                case "Oliwa": {
                    selectedIngredient = OliveOil;
                    break;
                }
                case "Pieprz mielony": {
                    selectedIngredient = Pepper;
                    break;
                }
                case "Płatki kukurydziane": {
                    selectedIngredient = CornFlakes;
                    break;
                }
                case "Płatki owsiane": {
                    selectedIngredient = OatFlakes;
                    break;
                }
                case "Ryż biały": {
                    selectedIngredient = WhiteRise;
                    break;
                }
                case "Ryż brązowy": {
                    selectedIngredient = BrownRise;
                    break;
                }
                case "Sól": {
                    selectedIngredient = Salt;
                    break;
                }
                case "Sól gruboziarnista": {
                    selectedIngredient = RockSalt;
                    break;
                }
                case "Spirytus": {
                    selectedIngredient = Alcohol;
                    break;
                }
                case "Śmietana 12%": {
                    selectedIngredient = Cream12;
                    break;
                }
                case "Śmietana 18%": {
                    selectedIngredient = Cream18;
                    break;
                }
                case "Śmietana 30%": {
                    selectedIngredient = Cream30;
                    break;
                }
                case "Woda": {
                    selectedIngredient = Water;
                    break;
                }
                case "Wino": {
                    selectedIngredient = Wine;
                    break;
                }
                case "Wódka": {
                    selectedIngredient = Vodka;
                    break;
                }
                default: {
                    selectedIngredient = Empty;
                    break;
                }
            }

            switch (sourceUnitSpinner.getSelectedItem().toString()) {

                //WEIGHT UNITS
                case "Gramy": {
                    selectedSourceUnit = Unit.GRAM;
                    break;
                }
                case "Dekagramy": {
                    selectedSourceUnit = Unit.DEKAGRAM;
                    break;
                }
                case "Kilogramy": {
                    selectedSourceUnit = Unit.KILOGRAM;
                    break;
                }
                case "Grany": {
                    selectedSourceUnit = Unit.GR;
                    break;
                }
                case "Dramy": {
                    selectedSourceUnit = Unit.DR;
                    break;
                }
                case "Uncje": {
                    selectedSourceUnit = Unit.OZ;
                    break;
                }
                case "Funty": {
                    selectedSourceUnit = Unit.LB;
                    break;
                }

                //VOLUME UNITS
                case "Mililitry": {
                    selectedSourceUnit = Unit.MILILITR;
                    break;
                }
                case "Litry": {
                    selectedSourceUnit = Unit.LITR;
                    break;
                }
                case "Uncje płynu": {
                    selectedSourceUnit = Unit.OZF;
                    break;
                }
                case "Pinty": {
                    selectedSourceUnit = Unit.PT;
                    break;
                }
                case "Kwarty": {
                    selectedSourceUnit = Unit.QT;
                    break;
                }
                case "Galony": {
                    selectedSourceUnit = Unit.GAL;
                    break;
                }

                // OTHER VOLUME UNITS
                case "Łyżki": {
                    selectedSourceUnit = Unit.SPOON;
                    break;
                }
                case "Łyżeczki": {
                    selectedSourceUnit = Unit.TEASPOON;
                    break;
                }
                case "Szklanki": {
                    selectedSourceUnit = Unit.GLASS;
                    break;
                }
                case "Szczypty": {
                    selectedSourceUnit = Unit.PINCH;
                    break;
                }
                default: {
                    selectedSourceUnit = Unit.NULL;
                    break;
                }
            }


            switch (destinyUnitSpinner.getSelectedItem().toString()) {
                //WEIGHT UNITS
                case "Gramy": {
                    selectedDestinyUnit = Unit.GRAM;
                    break;
                }
                case "Dekagramy": {
                    selectedDestinyUnit = Unit.DEKAGRAM;
                    break;
                }
                case "Kilogramy": {
                    selectedDestinyUnit = Unit.KILOGRAM;
                    break;
                }
                case "Grany": {
                    selectedDestinyUnit = Unit.GR;
                    break;
                }
                case "Dramy": {
                    selectedDestinyUnit = Unit.DR;
                    break;
                }
                case "Uncje": {
                    selectedDestinyUnit = Unit.OZ;
                    break;
                }
                case "Funty": {
                    selectedDestinyUnit = Unit.LB;
                    break;
                }

                //VOLUME UNITS
                case "Mililitry": {
                    selectedDestinyUnit = Unit.MILILITR;
                    break;
                }
                case "Litry": {
                    selectedDestinyUnit = Unit.LITR;
                    break;
                }
                case "Uncje płynu": {
                    selectedDestinyUnit = Unit.OZF;
                    break;
                }
                case "Pinty": {
                    selectedDestinyUnit = Unit.PT;
                    break;
                }
                case "Kwarty": {
                    selectedDestinyUnit = Unit.QT;
                    break;
                }
                case "Galony": {
                    selectedDestinyUnit = Unit.GAL;
                    break;
                }

                // OTHER VOLUME UNITS
                case "Łyżki": {
                    selectedDestinyUnit = Unit.SPOON;
                    break;
                }
                case "Łyżeczki": {
                    selectedDestinyUnit = Unit.TEASPOON;
                    break;
                }
                case "Szklanki": {
                    selectedDestinyUnit = Unit.GLASS;
                    break;
                }
                case "Szczypty": {
                    selectedDestinyUnit = Unit.PINCH;
                    break;
                }
                default: {
                    selectedDestinyUnit = Unit.NULL;
                    break;
                }
            }


            if (editTextValue.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Podaj ilość!",
                        Toast.LENGTH_LONG).show();


            } else {
                double value = Double.parseDouble(editTextValue.getText().toString());
                if (value <= 0) {
                    Toast.makeText(getActivity(), "Ta ilość jest zbyt mała!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Converter conventer = new Converter(selectedIngredient, value, selectedSourceUnit, selectedDestinyUnit);
                    resultValueEditText.setText(String.valueOf(conventer.calculate()));
                }
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Niepoprawne dane!",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void setCulinaryUnits() {
        arrayAdapterSourceUnit = ArrayAdapter.createFromResource(getActivity(), R.array.culinary_units, R.layout.spinner_item_unit);
        arrayAdapterSourceUnit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(arrayAdapterSourceUnit);

        arrayAdapterDestinySpinner = ArrayAdapter.createFromResource(getActivity(), R.array.culinary_units, R.layout.spinner_item_unit);
        arrayAdapterDestinySpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        destinyUnitSpinner.setAdapter(arrayAdapterDestinySpinner);
    }

    public void setPlUnits() {
        arrayAdapterSourceUnit = ArrayAdapter.createFromResource(getActivity(), R.array.pl_units, R.layout.spinner_item_unit);
        arrayAdapterSourceUnit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(arrayAdapterSourceUnit);

        arrayAdapterDestinySpinner = ArrayAdapter.createFromResource(getActivity(), R.array.pl_units, R.layout.spinner_item_unit);
        arrayAdapterDestinySpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        destinyUnitSpinner.setAdapter(arrayAdapterDestinySpinner);
    }

    public void setEngUnits() {
        arrayAdapterSourceUnit = ArrayAdapter.createFromResource(getActivity(), R.array.eng_units, R.layout.spinner_item_unit);
        arrayAdapterSourceUnit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(arrayAdapterSourceUnit);

        arrayAdapterDestinySpinner = ArrayAdapter.createFromResource(getActivity(), R.array.eng_units, R.layout.spinner_item_unit);
        arrayAdapterDestinySpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        destinyUnitSpinner.setAdapter(arrayAdapterDestinySpinner);
    }

    public void setPlEngUnits() {
        arrayAdapterSourceUnit = ArrayAdapter.createFromResource(getActivity(), R.array.pl_eng_units, R.layout.spinner_item_unit);
        arrayAdapterSourceUnit.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(arrayAdapterSourceUnit);

        arrayAdapterDestinySpinner = ArrayAdapter.createFromResource(getActivity(), R.array.pl_eng_units, R.layout.spinner_item_unit);
        arrayAdapterDestinySpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        destinyUnitSpinner.setAdapter(arrayAdapterDestinySpinner);
    }

}