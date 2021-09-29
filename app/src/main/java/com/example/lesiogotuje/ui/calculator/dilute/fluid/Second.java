package com.example.lesiogotuje.ui.calculator.dilute.fluid;

import com.example.lesiogotuje.ui.calculator.dilute.Unit;

public class Second extends Fluid{
    public Second(Type type, Unit unit, double value, double concentration) {
        super(type, unit, value, concentration);
    }
    public Second(Type type, Unit unit, double value) {
        super(type, unit, value);
    }
    public Second(Type type, Unit unit) {
        super(type, unit);
    }
}