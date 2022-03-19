package Model.Banks;

import Model.Bank;
import Model.Credit;
import Model.IJSONIO;

import Model.IMockData;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for Sber
 */
public class Sberbank extends Bank implements IJSONIO, IMockData {

    /**
     * Singleton realization
     *
     * @return Sberbank instance
     */
    public static synchronized Sberbank getInstance() {
        if (instance == null) {
            instance = new Sberbank();
        }
        return (Sberbank) instance;
    }

    public Sberbank() {
        this.name = "Sberbank";
        this.rate = 1.2d;
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
        this.creditOffers.add(credit);
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
            Files.write(Path.of("src//main//resources//db//sberData.json"), Collections.singleton(gson.toJson(Sberbank.getInstance().creditOffers)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addMockData() {
        Sberbank sber = Sberbank.getInstance();
        Credit credit1 = new Credit(sber, 100d, 12, Credit.Types.FLAT, true, false);
        Credit credit2 = new Credit(sber, 200d, 24, Credit.Types.GOODS, false, true);
        Credit credit3 = new Credit(sber, 300d, 12, Credit.Types.CAR, false, true);
        Credit credit4 = new Credit(sber, 500d, 36, Credit.Types.OTHER, true, false);
        Credit credit5 = new Credit(sber, 400d, 24, Credit.Types.GOODS, true, true);
        sber.addCredit(credit1);
        sber.addCredit(credit2);
        sber.addCredit(credit3);
        sber.addCredit(credit4);
        sber.addCredit(credit5);
    }
}
