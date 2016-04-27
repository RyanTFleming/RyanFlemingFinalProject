package edu.westga.ryanflemingfinalproject.Model;

import edu.westga.ryanflemingfinalproject.Controller.DBController;

/**
 * Class that performs the calculations for the app.
 *
 * @author Ryan T. Fleming
 */
public class GoalCalculator {
    /**
     * Based on 52 weeks a year/ 12 months
     */
    public final double AVERAGE_WEEKS_PER_MONTH = 4.33;

    private double hoursWorkedPerWeek;
    private double hourlyWage;

    /**
     * Constructor for the GoalCalculator
     * @param wage - the hourly wage
     * @param hoursPerWeek - number of hours worked per week on average
     */
    public GoalCalculator(double wage, double hoursPerWeek) {
        this.hourlyWage = wage;
        this.hoursWorkedPerWeek = hoursPerWeek;
    }

    /**
     * Returns the number of hours worked per month;
     * @return hours worked per month
     */
    public double hoursWorkedPerMonth() {
        return this.hoursWorkedPerWeek * AVERAGE_WEEKS_PER_MONTH;
    }

    /**
     * Calculates the gross monthly earnings
     * @return monthly earnings
     */
    public double monthlyGrossEarning() {
        return this.hourlyWage * this.hoursWorkedPerMonth();
    }

    /**
     * Calculates net monthly earnings after deducting expenses
     * @param totalExpenses - total monthly expenses
     * @return monthly earnings minus expenses
     */
    public double monthlyNetEarnings(double totalExpenses){
        return this.monthlyGrossEarning() - totalExpenses;
    }

    /**
     * Calculates the amount of money saved per hour that can be put towards a goal
     * @param totalExpenses - total expenses
     * @return amount saved per hour not spent on expenses
     */
    public double amountSavedPerHour(double totalExpenses) {
        return this.monthlyNetEarnings(totalExpenses) / this.hoursWorkedPerMonth();
    }

    /**
     * calculates the total amount of hours need to work to get to goal
     * @param totalExpenses - the monthly expenses
     * @param goal - value of the goal
     * @return the number of hours needed to earn goal
     */
    public double hoursNeededToGoal(double totalExpenses, double goal) {
        return goal / this.amountSavedPerHour(totalExpenses);
    }



}

