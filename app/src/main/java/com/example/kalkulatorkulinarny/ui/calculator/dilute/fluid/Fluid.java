package com.example.kalkulator.ui.calculator.dilute.fluid;

import com.example.kalkulator.ui.calculator.dilute.Unit;

public abstract class Fluid {

    private Type type;
    private Unit unit;
    private double value;
    private double concentration;

    public Fluid(Type type, Unit unit) {
        this.type = type;
        this.unit = unit;
        this.concentration = 0;
        this.value = 0;
    }

    public Fluid(Type type, Unit unit, double value) {
        this.type = type;
        this.unit = unit;
        this.value = value;
        this.concentration = 100;
    }

    public Fluid(Type type, Unit unit, double value, double concentration) {
        this.type = type;
        this.unit = unit;
        this.value = value;
        this.concentration = concentration;
    }


    public Type getType() {
        return type;
    }

    void setType(Type type) {
        this.type = type;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public void setValueType(Type type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }
}