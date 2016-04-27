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

    private double hoursWorkedPerWeek;
    private double hourlyWage;


    public GoalCalculator(double wage, double hoursPerWeek) {
        this.hourlyWage = wage;
        this.hoursWorkedPerWeek = hoursPerWeek;
    }

    public double hoursWorkedPerMonth() {
        return this.hoursWorkedPerWeek * AVERAGE_WEEKS_PER_MONTH;
    }

    public double monthlyEarning() {
        return this.hourlyWage * this.hoursWorkedPerMonth();
    }

}

