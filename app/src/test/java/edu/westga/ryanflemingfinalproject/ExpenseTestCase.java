package edu.westga.ryanflemingfinalproject;

import org.junit.Test;
import edu.westga.ryanflemingfinalproject.Model.Expense;

import static org.junit.Assert.assertEquals;

/**
 * Test case for the expense class.
 *
 * @author  Ryan T Fleming
 */
public class ExpenseTestCase {

    /**
     * Test to make sure constructor creates an expense with the
     * name "Test"
     */
    @Test
    public void testShouldReturnExpenseName() {
        Expense testExpense = new Expense("Test", 10.00);
        assertEquals("Test", testExpense.getName());
    }

    /**
     * Test to make sure constructor creates an expense with the
     * value 10.00
     */
    @Test
    public void testShouldReturnExpenseValue() {
        Expense testExpense = new Expense("Test", 10.00);
        assertEquals(10.00, testExpense.getValue(), .00);
    }

    /**
     * Test to make sure constructor creates an expense with the
     * value 0.00
     */
    @Test
    public void testShouldReturnExpenseValueOf0() {
        Expense testExpense = new Expense("Test", 00.00);
        assertEquals(00.00, testExpense.getValue(), .00);
    }
}
