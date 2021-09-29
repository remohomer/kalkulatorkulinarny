package com.example.lesiogotuje.ui.calculator.dilute;

public class Ingredient {
    private final String name;
    private final double weightToVolumeRatio;

    Ingredient(String name, double volumeToWeightRatio) {
        this.name = name;
        this.weightToVolumeRatio = volumeToWeightRatio;
    }

    public String getName() {
        return name;
    }

    public double getWeightToVolumeRatio() {
        return weightToVolumeRatio;
    }
}