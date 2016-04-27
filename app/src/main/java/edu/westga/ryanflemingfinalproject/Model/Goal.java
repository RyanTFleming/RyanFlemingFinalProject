package edu.westga.ryanflemingfinalproject.Model;

/**
 * Created by RyanT on 4/27/2016.
 */
public class Goal {

    private String name;
    private double cost;
    /**
     * Constructor for the goal model class
     * @param name - the name of the goal item
     * @param cost - the cost of the goal item
     */
    public Goal(String name, double cost) {
        if (name.equals(null)) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        this.name = name;
        this.cost = cost;
    }

    /**
     * returns the name of the item
     * @return this.name - the name of the item
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the cost of the of the item
     * @return this.cost - the cost of the item as a double
     */
    public double getCost() {
        return this.cost;
    }

}
