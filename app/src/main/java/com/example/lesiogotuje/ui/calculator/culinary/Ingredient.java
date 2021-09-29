package com.example.lesiogotuje.ui.calculator.culinary;

public class Ingredient {

    private final String name;
    private final double weightToVolumeRatio;

    public Ingredient(String name, double weightToVolumeRatio) {
        this.name = name;
        this.weightToVolumeRatio = weightToVolumeRatio;
    }

    public String getName() {
        return name;
    }

    public double getWeightToVolumeRatio() {
        return weightToVolumeRatio;
    }
}