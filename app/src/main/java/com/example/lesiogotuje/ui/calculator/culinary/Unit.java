package com.example.lesiogotuje.ui.calculator.culinary;

public enum Unit {

    GRAM("Gramy","Gram",1,true),
    DEKAGRAM("Dekagramy","Dekagram",10,true),
    KILOGRAM("Kilogramy","Kilogram",1000,true),
    GR("Grany","Gran",0.065,true),
    DR("Dramy","Dram",1.78,true),
    OZ("Uncje","Uncja",28.34952981,true),
    LB("Funty","Funt",453.59237,true),

    MILILITR("Mililitry","Mililitr",1,false),
    LITR("Litry","Litr",1000,false),
    OZF("Uncje płynu","Uncja płynu",28.41,false),
    PT("Pinty","Pint",568.26,false),
    QT("Kwarty","Kwart",1136,false),
    GAL("Galony","Galon",4546.09,false),

    GLASS("Szklanki","Szklanka",250,false),
    SPOON("Łyżki","Łyżka",15,false),
    TEASPOON("Łyżeczki","Łyżeczka",5,false),
    PINCH("Szczypty","Szczypta",0.5,false),

    NULL("Brak","Brak",-1,false);


    public final String name;
    public final String singularName;
    public final double value;
    public final boolean isWeight;

    Unit(String name, String singularName, double value, boolean isWeight) {
        this.name = name;
        this.singularName = singularName;
        this.value = value;
        this.isWeight = isWeight;
    }
    Unit(Unit unit) {
        this.name = unit.name;
        this.singularName = unit.singularName;
        this.value = unit.value;
        this.isWeight = unit.isWeight;
    }

    public String getName() {
        return name;
    }
    public String getSingularName() {
        return singularName;
    }
    public double getValue() {
        return value;
    }
    public double getRoundedValue() {
        return Math.round(value * 100.0) / 100.0;
    }

}

