package edu.westga.ryanflemingfinalproject.Model;

/**
 * Class that performs the calculations for the app.
 *
 * @author Ryan T. Fleming
 */
public class GoalCalculator {
    /**
     * Based on 52 weeks a year/ 12 months
     */
    private final double AVERAGE_WEEKS_PER_MONTH = 4.33;

    public GoalCalculator() {

    }

    public double hoursWorkedPerMonth(double hoursPerWeek) {
        return hoursPerWeek * AVERAGE_WEEKS_PER_MONTH;
    }
}

