package com.example.kalkulator.ui.calculator.dilute.fluid;

public enum Type {
    ALCOHOL("Alcohol"),
    WATER("Woda"),
    SOLUTION("Roztwor"),
    NULL("Brak");

    public final String name;

    Type(Type type) {
        this.name = type.getName();
    }

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}