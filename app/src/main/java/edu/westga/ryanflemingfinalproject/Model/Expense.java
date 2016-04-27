package edu.westga.ryanflemingfinalproject.Model;

/**
 * Models an expense object.
 *
 * @author Ryan Fleming
 * @version  4/26/2016
 */
public class Expense {

    private String expenseName;
    private double expenseValue;

    /**
     * Instantiates an expense object
     * @param name - the name of the expense
     * @param value - the value of the expense
     */
    public Expense(String name, double value) {
        this.expenseName = name;
        this.expenseValue = value;
    }

    /**
     * Returns the name of the expense
     *
     * @return the String of the expense Name
     */
    public String getName() {
        return this.expenseName;
    }

    /**
     * Returns the Value of the expense
     *
     * @return the double value of the expense value
     */
    public double getValue() {
        return this.expenseValue;
    }

    @Override
    public String toString() {
        return String.format("%10s:                     %-10.2f", this.expenseName, this.expenseValue);
    }


}
