package com.example.kalkulator.ui.calculator.dilute.fluid;

import com.example.kalkulator.ui.calculator.dilute.Unit;

public class Third extends Fluid {
    public Third(Type type, Unit unit, double value, double concentration) {
        super(type, unit, value, concentration);
    }
    public Third(Type type, Unit unit, double value) {
        super(type, unit, value);
    }
    public Third(Type type, Unit unit) {
        super(type, unit);
    }
}