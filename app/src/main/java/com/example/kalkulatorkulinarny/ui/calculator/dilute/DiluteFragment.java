package com.example.kalkulator.ui.calculator.dilute;

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

import com.example.kalkulator.MainActivity;
import com.example.kalkulator.R;
import com.example.kalkulator.databinding.FragmentCalculatorDiluteBinding;


public class DiluteFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentCalculatorDiluteBinding binding;

    private Unit firstUnit;
    private Unit secondUnit;
    private Unit thirdUnit;

    private Action action;

    private Spinner spinnerAction;
    private Spinner spinnerFirst;
    private Spinner spinnerSecond;
    private Spinner spinnerThird;

    private ArrayAdapter<CharSequence> arrayFirst;
    private ArrayAdapter<CharSequence> arraySecond;
    private ArrayAdapter<CharSequence> arrayThird;

    private SwitchCompat swEngDiluteUnits;
    private SwitchCompat swPlDiluteUnits;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalculatorDiluteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        String source = "" +
                "<h3>JEDNOSTKI KULINARNE</h3>" +
                "<br>" +
                "<p>" +
                "1 " + com.example.kalkulator.ui.calculator.culinary.Unit.PINCH.getSingularName() + " = 0,ml" + "<br>" +
                "1 " + com.example.kalkulator.ui.calculator.culinary.Unit.TEASPOON.getSingularName() + " = 5ml" + "<br>" +
                "1 " + com.example.kalkulator.ui.calculator.culinary.Unit.SPOON.getSingularName() + " = 15ml" + "<br>" +
                "1 " + com.example.kalkulator.ui.calculator.culinary.Unit.GLASS.getSingularName() + " = 250ml" + "<br>" +
                "</p>" +
                "<br>" +
                "<h3>POLSKIE</h3>" +
                "<br>" +
                "<p>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.GRAM.getSingularName() + "</strong>(g) = <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.MILILITR.getRoundedValue() + "ml</strong> (wody)" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.DEKAGRAM.getSingularName() + "</strong>(dag) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.DEKAGRAM.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.KILOGRAM.getSingularName() + "</strong>(kg) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.KILOGRAM.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.MILILITR.getSingularName() + "</strong>(ml) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.GRAM.getRoundedValue()+"g</strong> (wody)" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.LITR.getSingularName() + "</strong>(l) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.LITR.getRoundedValue()+"ml</strong>" + "<br>" +
                "</p>" +
                "<br>" +
                "<h3>ANGIELSKIE</h3>" +
                "<br>" +
                "<p>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.GR.getSingularName() + "</strong>(gr) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.GR.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.DR.getSingularName() + "</strong>(dr) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.DR.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.OZ.getSingularName() + "</strong>(oz) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.OZ.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.LB.getSingularName() + "</strong>(lb) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.LB.getRoundedValue()+"g</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.OZF.getSingularName() + "</strong>(oz(fl)) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.OZF.getRoundedValue()+"ml</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.PT.getSingularName() + "</strong>(pt) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.PT.getRoundedValue()+"ml</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.QT.getSingularName() + "</strong>(qt) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.QT.getRoundedValue()+"ml</strong>" + "<br>" +
                "1 <strong>" + com.example.kalkulator.ui.calculator.culinary.Unit.GAL.getSingularName() + "</strong>(gal) = <strong>"+ com.example.kalkulator.ui.calculator.culinary.Unit.GAL.getRoundedValue()+"ml</strong>" + "<br>" +
                "</p>" +
                "";

        ImageView iVMoreInfo;
        TextView tVLegend = binding.idTvDiluteLegend;
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
                    tVMoreInfo.setTextColor(0xFFf7466a);
                    iVMoreInfo.setImageResource(R.drawable.ic_arrow_up);
                } else {
                    tVLegend.setVisibility(View.GONE);
                    tVMoreInfo.setText("Pokaż więcej");
                    tVMoreInfo.setTextColor(0xFF27aa6d);
                    iVMoreInfo.setImageResource(R.drawable.ic_arrow_down);
                }
            }
        });


        TextView textViewDFirst = binding.textViewDFirst;
        TextView textViewDSecond = binding.textViewDSecond;
        TextView textViewDThird = binding.textViewDThird;

        EditText editTextDFirst = binding.editTextDFirst;
        EditText editTextDSecond = binding.editTextDSecond;
        EditText editTextDThird = binding.editTextDThird;

        EditText editTextDAConcentration = binding.editTextDAConcentration;
        EditText editTextDSConcentration = binding.editTextDSConcentration;
        editTextDSConcentration.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkAllEditTextandCalculate(editTextDAConcentration,
                            editTextDSConcentration,
                            editTextDFirst,
                            editTextDSecond,
                            editTextDThird);
                    handled = true;
                }
                return handled;
            }
        });

        spinnerAction = binding.spinnerDAction;
        ArrayAdapter<CharSequence> arrayAction = ArrayAdapter.createFromResource(getActivity(), R.array.dilute_action, R.layout.spinner_item_action);
        arrayAction.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerAction.setAdapter(arrayAction);
        spinnerAction.setOnItemSelectedListener(this);
        spinnerAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (spinnerAction.getSelectedItem().toString()) {
                    case "Ile wody do alkoholu": {
                        action = Action.HOW_MUCH_WATER;
                        textViewDFirst.setText("Ile masz alkoholu?");
                        textViewDSecond.setText("Dodaj wody:");
                        textViewDThird.setText("Cały roztwór:");
                        break;
                    }
                    case "Ile alkoholu do wody": {
                        action = Action.HOW_MUCH_ALCOHOL;
                        textViewDFirst.setText("Ile masz wody?");
                        textViewDSecond.setText("Dodaj alkoholu:");
                        textViewDThird.setText("Cały roztwór:");
                        break;
                    }
                    case "Ile wody i alkoholu do roztworu": {
                        action = Action.SOLUTION_PROPORTION;
                        textViewDFirst.setText("Ile chcesz roztworu?");
                        textViewDSecond.setText("Dodaj alkoholu:");
                        textViewDThird.setText("Dodaj wody:");
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerFirst = binding.spinnerDFirst;
        spinnerFirst.setEnabled(false);
        spinnerFirst.setClickable(false);
        arrayFirst = ArrayAdapter.createFromResource(getActivity(), R.array.pl_dilute_units, R.layout.spinner_item_unit);
        arrayFirst.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFirst.setAdapter(arrayFirst);
        spinnerFirst.setOnItemSelectedListener(this);
        spinnerFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (spinnerFirst.getSelectedItem().toString()) {

                    // WEIGHT
                    case "g": {
                        firstUnit = Unit.GRAM;
                        break;
                    }
                    case "dag": {
                        firstUnit = Unit.DEKAGRAM;
                        break;
                    }
                    case "kg": {
                        firstUnit = Unit.KILOGRAM;
                        break;
                    }
                    case "gr": {
                        firstUnit = Unit.GR;
                        break;
                    }
                    case "dr": {
                        firstUnit = Unit.DR;
                        break;
                    }
                    case "oz": {
                        firstUnit = Unit.OZ;
                        break;
                    }
                    case "lb": {
                        firstUnit = Unit.LB;
                        break;
                    }

                    // VOLUME
                    case "ml": {
                        firstUnit = Unit.MILILITR;
                        break;
                    }
                    case "l": {
                        firstUnit = Unit.LITR;
                        break;
                    }
                    case "oz(fl)": {
                        firstUnit = Unit.OZF;
                        break;
                    }
                    case "pt": {
                        firstUnit = Unit.PT;
                        break;
                    }
                    case "qt": {
                        firstUnit = Unit.QT;
                        break;
                    }
                    case "gal": {
                        firstUnit = Unit.GAL;
                        break;
                    }

                    // OTHER
                    case "łyż.": {
                        firstUnit = Unit.SPOON;
                        break;
                    }
                    case "łyżecz.": {
                        firstUnit = Unit.TEASPOON;
                        break;
                    }
                    case "szkl.": {
                        firstUnit = Unit.GLASS;
                        break;
                    }
                    default: {
                        firstUnit = Unit.NULL;
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerSecond = binding.spinnerDSecond;
        spinnerSecond.setEnabled(true);
        spinnerSecond.setClickable(true);
        arraySecond = ArrayAdapter.createFromResource(getActivity(), R.array.pl_dilute_units, R.layout.spinner_item_unit);
        arraySecond.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSecond.setAdapter(arraySecond);
        spinnerSecond.setOnItemSelectedListener(this);
        spinnerSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinnerSecond.getSelectedItem().toString()) {

                    // WEIGHT
                    case "g": {
                        secondUnit = Unit.GRAM;
                        break;
                    }
                    case "dag": {
                        secondUnit = Unit.DEKAGRAM;
                        break;
                    }
                    case "kg": {
                        secondUnit = Unit.KILOGRAM;
                        break;
                    }
                    case "gr": {
                        secondUnit = Unit.GR;
                        break;
                    }
                    case "dr": {
                        secondUnit = Unit.DR;
                        break;
                    }
                    case "oz": {
                        secondUnit = Unit.OZ;
                        break;
                    }
                    case "lb": {
                        secondUnit = Unit.LB;
                        break;
                    }

                    // VOLUME
                    case "ml": {
                        secondUnit = Unit.MILILITR;
                        break;
                    }
                    case "l": {
                        secondUnit = Unit.LITR;
                        break;
                    }
                    case "oz(fl)": {
                        secondUnit = Unit.OZF;
                        break;
                    }
                    case "pt": {
                        secondUnit = Unit.PT;
                        break;
                    }
                    case "qt": {
                        secondUnit = Unit.QT;
                        break;
                    }
                    case "gal": {
                        secondUnit = Unit.GAL;
                        break;
                    }

                    // OTHER
                    case "łyż.": {
                        secondUnit = Unit.SPOON;
                        break;
                    }
                    case "łyżecz.": {
                        secondUnit = Unit.TEASPOON;
                        break;
                    }
                    case "szkl.": {
                        secondUnit = Unit.GLASS;
                        break;
                    }
                    default: {
                        secondUnit = Unit.NULL;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerThird = binding.spinnerDThird;
        spinnerThird.setEnabled(true);
        spinnerThird.setClickable(true);
        arrayThird = ArrayAdapter.createFromResource(getActivity(), R.array.pl_dilute_units, R.layout.spinner_item_unit);
        arrayThird.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerThird.setAdapter(arrayThird);
        spinnerThird.setOnItemSelectedListener(this);
        spinnerThird.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (spinnerThird.getSelectedItem().toString()) {
                    // WEIGHT
                    case "g": {
                        thirdUnit = Unit.GRAM;
                        break;
                    }
                    case "dag": {
                        thirdUnit = Unit.DEKAGRAM;
                        break;
                    }
                    case "kg": {
                        thirdUnit = Unit.KILOGRAM;
                        break;
                    }
                    case "gr": {
                        thirdUnit = Unit.GR;
                        break;
                    }
                    case "dr": {
                        thirdUnit = Unit.DR;
                        break;
                    }
                    case "oz": {
                        thirdUnit = Unit.OZ;
                        break;
                    }
                    case "lb": {
                        thirdUnit = Unit.LB;
                        break;
                    }

                    // VOLUME
                    case "ml": {
                        thirdUnit = Unit.MILILITR;
                        break;
                    }
                    case "l": {
                        thirdUnit = Unit.LITR;
                        break;
                    }
                    case "oz(fl)": {
                        thirdUnit = Unit.OZF;
                        break;
                    }
                    case "pt": {
                        thirdUnit = Unit.PT;
                        break;
                    }
                    case "qt": {
                        thirdUnit = Unit.QT;
                        break;
                    }
                    case "gal": {
                        thirdUnit = Unit.GAL;
                        break;
                    }

                    // OTHER
                    case "łyż.": {
                        thirdUnit = Unit.SPOON;
                        break;
                    }
                    case "łyżecz.": {
                        thirdUnit = Unit.TEASPOON;
                        break;
                    }
                    case "szkl.": {
                        thirdUnit = Unit.GLASS;
                        break;
                    }
                    default: {
                        thirdUnit = Unit.NULL;
                        break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button buttonCount = binding.buttonDCount;
        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAllEditTextandCalculate(editTextDAConcentration,
                        editTextDSConcentration,
                        editTextDFirst,
                        editTextDSecond,
                        editTextDThird);
            }
        });

        swPlDiluteUnits = binding.swPlDiluteUnits;
        swPlDiluteUnits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton plButtonView, boolean isChecked) {
                if (plButtonView.isChecked()) {
                    if (swEngDiluteUnits.isChecked()) {
                        setPlEngDiluteUnits();
                    } else {
                        setPlDiluteUnits();
                    }

                } else {
                    if (swEngDiluteUnits.isChecked()) {
                        setEngDiluteUnits();
                    } else {
                        setCulinaryDiluteUnits();
                    }
                }
            }
        });

        swEngDiluteUnits = binding.swEngDiluteUnits;
        swEngDiluteUnits.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton engButtonView, boolean isChecked) {

                if (engButtonView.isChecked()) {
                    if (swPlDiluteUnits.isChecked()) {
                        setPlEngDiluteUnits();
                    } else {
                        setEngDiluteUnits();
                    }

                } else {
                    if (swPlDiluteUnits.isChecked()) {
                        setPlDiluteUnits();
                    } else {
                        setCulinaryDiluteUnits();
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

    public void checkAllEditTextandCalculate(EditText editTextDAConcentration,
                                             EditText editTextDSConcentration,
                                             EditText editTextDFirst,
                                             EditText editTextDSecond,
                                             EditText editTextDThird) {
        MainActivity.closeKeyBoard(getActivity());

        try {
            if (editTextDAConcentration.getText().toString().equals("") ||
                    editTextDSConcentration.getText().toString().equals("") ||
                    editTextDFirst.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Wypełnij wszystkie pola",
                        Toast.LENGTH_SHORT).show();
            } else {
                double value = Double.parseDouble(editTextDFirst.getText().toString());
                double alcoholConcentration = Double.parseDouble(editTextDAConcentration.getText().toString());
                double solutionConcentration = Double.parseDouble(editTextDSConcentration.getText().toString());

                if (alcoholConcentration <= 0 || solutionConcentration <= 0 || value <= 0) {
                    Toast.makeText(getActivity(), "Wprowadź dane większe od zera",
                            Toast.LENGTH_SHORT).show();
                } else if (alcoholConcentration < solutionConcentration) {
                    Toast.makeText(getActivity(), "Stężenie alkoholu nie może być mniejsze od stężenia roztworu docelowego",
                            Toast.LENGTH_SHORT).show();
                } else if (alcoholConcentration > 100 || solutionConcentration > 100) {
                    Toast.makeText(getActivity(), "Nie ma alkoholu o tak wysokim stężeniu",
                            Toast.LENGTH_SHORT).show();
                } else {

                    Dilute dilute = new Dilute(action, alcoholConcentration, solutionConcentration, value);

                    switch (spinnerFirst.getSelectedItem().toString()) {
                        case "ml": {
//                            dilute.getFirst().setUnit(Unit.MILILITR);
                            dilute.getSecond().setUnit(Unit.MILILITR);
                            dilute.getThird().setUnit(Unit.MILILITR);
                            break;
                        }
                        case "l": {
//                            dilute.getFirst().setUnit(Unit.LITR);
                            dilute.getSecond().setUnit(Unit.LITR);
                            dilute.getThird().setUnit(Unit.LITR);
                            break;
                        }
                        case "g": {
//                            dilute.getFirst().setUnit(Unit.GRAM);
                            dilute.getSecond().setUnit(Unit.GRAM);
                            dilute.getThird().setUnit(Unit.GRAM);
                            break;
                        }
                        case "kg": {
//                            dilute.getFirst().setUnit(Unit.KILOGRAM);
                            dilute.getSecond().setUnit(Unit.KILOGRAM);
                            dilute.getThird().setUnit(Unit.KILOGRAM);
                            break;
                        }
                        case "łyż.": {
//                            dilute.getFirst().setUnit(Unit.SPOON);
                            dilute.getSecond().setUnit(Unit.SPOON);
                            dilute.getThird().setUnit(Unit.SPOON);
                            break;
                        }
                        case "łyżecz.": {
//                            dilute.getFirst().setUnit(Unit.TEASPOON);
                            dilute.getSecond().setUnit(Unit.TEASPOON);
                            dilute.getThird().setUnit(Unit.TEASPOON);
                            break;
                        }
                        case "szkl.": {
//                            dilute.getFirst().setUnit(Unit.GLASS);
                            dilute.getSecond().setUnit(Unit.GLASS);
                            dilute.getThird().setUnit(Unit.GLASS);
                            break;
                        }
                        default: {
                            break;
                        }
                    }



//                    Converter convertFirst = new Converter(dilute.getFirst(),Unit.MILILITR);
//                    dilute.getFirst().setValue(convertFirst.calculate());

                    dilute.calculate();

                    Converter convertSecond = new Converter(dilute.getSecond(), secondUnit);
                    Converter convertThird = new Converter(dilute.getThird(), thirdUnit);

                    editTextDSecond.setText(String.valueOf(convertSecond.calculate()));
                    editTextDThird.setText(String.valueOf(convertThird.calculate()));
                }
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Wprowadź poprawne dane",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void setCulinaryDiluteUnits() {
        arraySecond = ArrayAdapter.createFromResource(getActivity(), R.array.culinary_dilute_units, R.layout.spinner_item_unit);
        arraySecond.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSecond.setAdapter(arraySecond);

        arrayThird = ArrayAdapter.createFromResource(getActivity(), R.array.culinary_dilute_third_unit, R.layout.spinner_item_unit);
        arrayThird.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerThird.setAdapter(arrayThird);
    }

    public void setPlDiluteUnits() {
        arraySecond = ArrayAdapter.createFromResource(getActivity(), R.array.pl_dilute_units, R.layout.spinner_item_unit);
        arraySecond.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSecond.setAdapter(arraySecond);

        arrayThird = ArrayAdapter.createFromResource(getActivity(), R.array.pl_dilute_third_unit, R.layout.spinner_item_unit);
        arrayThird.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerThird.setAdapter(arrayThird);
    }

    public void setEngDiluteUnits() {
        arraySecond = ArrayAdapter.createFromResource(getActivity(), R.array.eng_dilute_units, R.layout.spinner_item_unit);
        arraySecond.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSecond.setAdapter(arraySecond);

        arrayThird = ArrayAdapter.createFromResource(getActivity(), R.array.eng_dilute_third_unit, R.layout.spinner_item_unit);
        arrayThird.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerThird.setAdapter(arrayThird);
    }

    public void setPlEngDiluteUnits() {
        arraySecond = ArrayAdapter.createFromResource(getActivity(), R.array.pl_eng_dilute_units, R.layout.spinner_item_unit);
        arraySecond.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSecond.setAdapter(arraySecond);

        arrayThird = ArrayAdapter.createFromResource(getActivity(), R.array.pl_eng_dilute_third_unit, R.layout.spinner_item_unit);
        arrayThird.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerThird.setAdapter(arrayThird);
    }

}