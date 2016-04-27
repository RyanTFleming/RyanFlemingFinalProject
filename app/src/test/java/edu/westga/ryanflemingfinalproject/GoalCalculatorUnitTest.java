package edu.westga.ryanflemingfinalproject;

import org.junit.Test;

import edu.westga.ryanflemingfinalproject.Model.GoalCalculator;

import static junit.framework.Assert.assertEquals;

/**
 * Created by RyanT on 4/27/2016.
 */
public class GoalCalculatorUnitTest {

    private GoalCalculator testCalc = new GoalCalculator(12.00, 30.00);

    /**
     * Test to ensure the correct number of hours worked per month;
     */
    @Test
    public void shouldReturnCorrectHoursWorkedPerMonth() {
        assertEquals(30.00 * testCalc.AVERAGE_WEEKS_PER_MONTH, testCalc.hoursWorkedPerMonth());
    }

    /**
     * Test to ensure the correct total earned each month is correct.
     */
    @Test
    public void shouldReturnTheCorrectAmountEarnedPerMonth() {
        assertEquals(30.00 * testCalc.AVERAGE_WEEKS_PER_MONTH * 12, testCalc.monthlyGrossEarning());
    }

    /**
     * Test to make sure the calcualtion returns the net earnings after deducting the average monthly
     * expenses.
     */
    @Test
    public void shouldReturnTheCorrectNetIncome() {
        assertEquals(30.00 * testCalc.AVERAGE_WEEKS_PER_MONTH * 12 - 400.00, testCalc.monthlyNetEarnings(400.00));
    }

    /**
     * Test to ensure the amount saved per hour is properly calculated
     */
    @Test
    public void shouldReturnTheCorrectAmountOfMoneySavedPerHourWorked() {
        assertEquals(9.69, this.testCalc.amountSavedPerHour(300.00), 0.001);
    }

    /**
     * Test to ensure the correct number of hours needed to worked is returned
     */
    @Test
    public void shouldReturnTheCorrectNumberOfHoursNeededToEarnGoal() {
        assertEquals(51.597, this.testCalc.hoursNeededToGoal(300, 500), .001);
    }
}

