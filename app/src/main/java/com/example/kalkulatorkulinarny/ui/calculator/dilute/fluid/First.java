package com.example.kalkulator.ui.calculator.dilute.fluid;

import com.example.kalkulator.ui.calculator.dilute.Unit;

public class First extends Fluid{
    public First(Type type, Unit unit, double value, double concentration) {
        super(type, unit, value, concentration);
    }
    public First(Type type, Unit unit, double value) {
        super(type, unit, value);
    }
    public First(Type type, Unit unit) {
        super(type, unit);
    }
}