package edu.westga.ryanflemingfinalproject;

import org.junit.Test;

import edu.westga.ryanflemingfinalproject.Model.Goal;

import static org.junit.Assert.assertEquals;

/**
 * Test case for the Goal Model class
 *
 * @author Ryan T. Fleming
 */
public class GoalUnitTest {

    /**
     * Test to make sure the name of the goal is properly returned
     */
    @Test
    public void shouldReturnNameOfGoal() {
        Goal testGoal = new Goal("TestGoal", 300.00);
        assertEquals("TestGoal", testGoal.getName());
    }

    /**
     * Test to make sure the goal returns the cost
     */
    @Test
    public void shouldReturnCostOfGoalAs300() {
        Goal testGoal = new Goal("TestGoal", 300.00);
        assertEquals(300.00, testGoal.getCost(), .00);
    }

    /**
     * Test to make sure the goal returns cost with 2 decimal places
     */
    @Test
    public void shouldReturnCostOfGoalAs25Point25() {
        Goal testGoal = new Goal("TestGoal", 25.25);
        assertEquals(25.25, testGoal.getCost(), .00);
    }

    /**
     * Test to make sure a cost of zero is returned
     */
    @Test
    public void shouldReturnCostOfGoalAs0() {
        Goal testGoal = new Goal("TestGoal", 0.00);
        assertEquals(0.00, testGoal.getCost(), .00);
    }
}
