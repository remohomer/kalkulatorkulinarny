package com.example.lesiogotuje.ui.calculator.dilute;

public enum Unit {
    GRAM("Gramy",1,true),
    DEKAGRAM("Dekagramy",10,true),
    KILOGRAM("Kilogramy",1000,true),
    GR("Grany",0.065,true),
    DR("Dramy",1.78,true),
    OZ("Uncje",28.34952981,true),
    LB("Funty",453.59237,true),

    MILILITR("Mililitry",1,false),
    LITR("Litry",1000,false),
    OZF("Uncje płynu",28.41,false),
    PT("Pinty",568.26,false),
    QT("Kwarty",1136,false),
    GAL("Galony",4546.09,false),

    GLASS("Szklanki",250,false),
    SPOON("Łyżki",15,false),
    TEASPOON("Łyżeczki",5,false),
    PINCH("Szczypty",0.5,false),

    NULL("Brak",-1,false);

    private final String name;
    private final double value;
    private  boolean isWeight;

    Unit(Unit unit) {
        this.name = unit.name;
        this.value = unit.value;
        this.isWeight = unit.isWeight;
    }

    Unit(String name, double value, boolean isWeight) {
        this.name = name;
        this.value = value;
        this.isWeight = isWeight;
    }

    public String getName() {
        return name;
    }

    public boolean isWeight() {
        return isWeight;
    }

    public double getValue () {
        return value;
    }
}