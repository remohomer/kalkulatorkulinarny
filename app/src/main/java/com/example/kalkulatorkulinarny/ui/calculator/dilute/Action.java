package com.example.kalkulator.ui.calculator.dilute;

public enum Action {
    HOW_MUCH_WATER("Ile wody do alkoholu"),
    HOW_MUCH_ALCOHOL("Ile alkoholu do wody"),
    SOLUTION_PROPORTION("Ile wody i alkoholu do roztworu"),
    NULL("Brak");

    public final String name;

    Action(Action action) {
        this.name = action.getName();
    }

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}