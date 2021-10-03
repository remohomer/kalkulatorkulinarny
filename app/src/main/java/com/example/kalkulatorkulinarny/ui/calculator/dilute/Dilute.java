package com.example.kalkulator.ui.calculator.dilute;

import com.example.kalkulator.ui.calculator.dilute.fluid.*;

public class Dilute {

    private final Action action;

    private Fluid first;
    private Fluid second;
    private Fluid third;

    private final double alcoholConcentration;
    private final double solutionConcentration;
    private final double waterConcetration = 0;

    Dilute(final Action action, final double alcoholConcentration, final double solutionConcentration, final double value) {

        this.alcoholConcentration = alcoholConcentration;
        this.solutionConcentration = solutionConcentration;

        switch (action.getName()) {
            case "Ile wody do alkoholu": {
                first = new First(Type.ALCOHOL, Unit.MILILITR, value, this.alcoholConcentration);
                second = new Second(Type.WATER, Unit.MILILITR, value, this.waterConcetration);
                third = new Third(Type.SOLUTION, Unit.MILILITR, value, this.solutionConcentration);
                break;
            }
            case "Ile alkoholu do wody": {
                first = new First(Type.WATER, Unit.MILILITR, value, this.waterConcetration);
                second = new Second(Type.ALCOHOL, Unit.MILILITR, value, this.alcoholConcentration);
                third = new Third(Type.SOLUTION, Unit.MILILITR, value, this.solutionConcentration);
                break;
            }
            case "Ile wody i alkoholu do roztworu": {
                first = new First(Type.SOLUTION, Unit.MILILITR, value, this.solutionConcentration);
                second = new Second(Type.ALCOHOL, Unit.MILILITR, value, this.alcoholConcentration);
                third = new Third(Type.WATER, Unit.MILILITR, value, this.waterConcetration);
                break;
            }
        }
        this.action = action;
    }

    public void calculate() {

        double concentrationRatio = this.alcoholConcentration / this.solutionConcentration;
        if (action.getName().equals(Action.HOW_MUCH_WATER.getName())) {
            second.setValue(concentrationRatio * first.getValue() - first.getValue());
            third.setValue(first.getValue() + second.getValue());
        } else if (action.getName().equals(Action.HOW_MUCH_ALCOHOL.getName())) {

            third.setValue(first.getValue() / (alcoholConcentration - solutionConcentration) * alcoholConcentration );
            second.setValue(third.getValue() - first.getValue());
        } else if (action.getName().equals(Action.SOLUTION_PROPORTION.getName())) {
            third.setValue(first.getValue() - first.getValue() / concentrationRatio);
            second.setValue(first.getValue() - third.getValue());
        }
    }

    public Action getAction() {
        return action;
    }

    public Fluid getFirst() {
        return first;
    }

    public Fluid getSecond() {
        return second;
    }

    public Fluid getThird() {
        return third;
    }
}