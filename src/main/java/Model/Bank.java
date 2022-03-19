package Model;

import java.util.List;

/**
 * Basic class for banks
 */
public abstract class Bank {

    protected static Bank instance;

    /**
     * Bank's name
     */
    protected String name;

    /**
     * Credit rate
     */
    protected double rate;

    /**
     * List of bank's credit offers
     */
    protected List<Credit> creditOffers;

    /**
     * Method for calculating a credit
     *
     * @param sum     Basic credit sum
     * @param term    Credit length in days
     * @param prolong True if prolongation is available
     * @return calculated sum
     */
    public abstract double calculateCredit(double sum, int term, boolean prolong);

    public abstract void addCredit(Credit credit);

    public abstract String getName();

    public abstract double getRate();
}
