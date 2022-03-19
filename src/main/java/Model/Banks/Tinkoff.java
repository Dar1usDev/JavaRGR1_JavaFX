package Model.Banks;

import Model.Bank;
import Model.Credit;
import Model.IJSONIO;
import com.google.gson.Gson;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for Tinkoff
 */
public class Tinkoff extends Bank implements IJSONIO {

    /**
     * Singleton realization
     *
     * @return Tinkoff instance
     */
    public static synchronized Tinkoff getInstance() {
        if (instance == null) {
            instance = new Tinkoff();
        }
        return (Tinkoff) instance;
    }

    public Tinkoff() {
        this.name = "Tinkoff";
        this.rate = 1.4d;
        this.creditOffers = new ArrayList<>();
    }

    /**
     * Method for calculating a credit
     *
     * @param sum     Basic credit sum
     * @param term    Credit length in days
     * @param prolong True if prolongation is available
     * @return calculated sum
     */
    @Override
    public double calculateCredit(double sum, int term, boolean prolong) {
        return 0;
    }

    @Override
    public void addCredit(Credit credit) {
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getRate() {
        return this.rate;
    }

    @Override
    public void readJSON() {

    }

    @Override
    public void writeJSON() {
        Gson gson = new Gson();
        try {
            Files.write(Path.of("src//main//resources//db//tinkoffData.json"), Collections.singleton(gson.toJson(Tinkoff.getInstance().creditOffers)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
