package com.example.lesiogotuje.ui.calculator.dilute;

import com.example.lesiogotuje.ui.calculator.dilute.fluid.Fluid;
import com.example.lesiogotuje.ui.calculator.dilute.fluid.Type;

public class Converter {

    public Unit destinyUnit;
    public Fluid fluid;

    Ingredient ingredientEtanol = new Ingredient("Etanol", 0.66);
    Ingredient ingredientWater = new Ingredient("Woda", 1);
    Ingredient ingredientEmpty = new Ingredient("Empty", 1);

    Converter(Fluid fluid, Unit destinyUnit) {
        this.fluid = fluid;
        this.destinyUnit = destinyUnit;
    }

    public double calculate() {

        double result;
        if (fluid.getType() == Type.ALCOHOL) {
            if (fluid.getUnit().isWeight() && !destinyUnit.isWeight()) {
                result = ((fluid.getValue() / ingredientEtanol.getWeightToVolumeRatio()) * fluid.getUnit().getValue()) / destinyUnit.getValue();
            } else if (!fluid.getUnit().isWeight() && destinyUnit.isWeight()) {
                result = ((fluid.getValue() * ingredientEtanol.getWeightToVolumeRatio()) * fluid.getUnit().getValue()) / destinyUnit.getValue();
            } else {
                result = (fluid.getValue() * fluid.getUnit().getValue()) / destinyUnit.getValue();
            }
        } else if (fluid.getType() == Type.WATER) {
            if (fluid.getUnit().isWeight() && !destinyUnit.isWeight()) {
                result = ((fluid.getValue() / ingredientWater.getWeightToVolumeRatio()) * fluid.getUnit().getValue()) / destinyUnit.getValue();
            } else if (!fluid.getUnit().isWeight() && destinyUnit.isWeight()) {
                result = ((fluid.getValue() * ingredientWater.getWeightToVolumeRatio()) * fluid.getUnit().getValue()) / destinyUnit.getValue();
            } else {
                result = (fluid.getValue() * fluid.getUnit().getValue()) / destinyUnit.getValue();
            }
        } else if (fluid.getType() == Type.SOLUTION) {
            if (fluid.getUnit().isWeight() && !destinyUnit.isWeight()) {
                result = ((fluid.getValue() / ingredientEmpty.getWeightToVolumeRatio()) * fluid.getUnit().getValue()) / destinyUnit.getValue();
            } else if (!fluid.getUnit().isWeight() && destinyUnit.isWeight()) {
                result = ((fluid.getValue() * ingredientEmpty.getWeightToVolumeRatio()) * fluid.getUnit().getValue()) / destinyUnit.getValue();
            } else {
                result = (fluid.getValue() * fluid.getUnit().getValue()) / destinyUnit.getValue();
            }
        } else {
            result = -1;
        }
        return Math.round(result * 100.0) / 100.0;
    }
}