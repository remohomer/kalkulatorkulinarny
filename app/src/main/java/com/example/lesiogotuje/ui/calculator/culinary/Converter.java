package com.example.lesiogotuje.ui.calculator.culinary;

public class Converter {

    private final Ingredient ingredient;
    private final double value;
    private final Unit sourceUnit;
    private final Unit destinyUnit;

    public Converter(Ingredient ingredient, double value, Unit sourceUnit, Unit destinyUnit) {
        this.ingredient = ingredient;
        this.value = value;
        this.sourceUnit = sourceUnit;
        this.destinyUnit = destinyUnit;
    }

    public double calculate() {
        double result;
        if (sourceUnit.isWeight && !destinyUnit.isWeight) {
            result = ((value / ingredient.getWeightToVolumeRatio()) * sourceUnit.value) / destinyUnit.value;
        } else if (!sourceUnit.isWeight && destinyUnit.isWeight) {
            result = ((value * ingredient.getWeightToVolumeRatio()) * sourceUnit.value) / destinyUnit.value;
        } else {
            result = (value * sourceUnit.value) / destinyUnit.value;
        }
        return Math.round(result * 100.0) / 100.0;
    }

}
