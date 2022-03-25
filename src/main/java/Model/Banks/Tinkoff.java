package Model.Banks;

import Control.Log;
import Model.Bank;
import Model.Credit;
import Model.IJSONIO;
import Model.IMockData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for Tinkoff
 */
public class Tinkoff extends Bank implements IJSONIO, IMockData {

    /**
     * Singlton object
     */
    static private Tinkoff instance;

    /**
     * Singleton realization
     *
     * @return Tinkoff instance
     */
    public static synchronized Tinkoff getInstance() {
        if (instance == null) {
            instance = new Tinkoff();
        }
        return instance;
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
    public List<Credit> getCreditOffers() {
        return this.creditOffers;
    }

    @Override
    public void readJSON() {
        Runnable readRunnable = () -> {
            try {
                String jsonData = Files.readString(Path.of("src//main//resources//db//tinkoffData.json"));
                Tinkoff.getInstance().creditOffers = new Gson().fromJson(jsonData, new TypeToken<List<Credit>>() {
                }.getType());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Thread readThread = new Thread(readRunnable, "tinkoffReadThread");
        readThread.start();

        try {
            readThread.join();
        } catch (InterruptedException ignored) {
            // :)
        }


        Log.write(Tinkoff.getInstance().creditOffers.size() + " objects were read for Tinkoff");
    }

    @Override
    public void writeJSON() {
        Runnable readRunnable = () -> {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            try {
                Files.write(Path.of("src//main//resources//db//tinkoffData.json"), Collections.singleton(gson.toJson(Tinkoff.getInstance().creditOffers)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Thread writeThread = new Thread(readRunnable, "tinkoffWriteThread");
        writeThread.start();

        try {
            writeThread.join();
        } catch (InterruptedException ignored) {
            // :)
        }
    }

    @Override
    public void addMockData() {
        Tinkoff tinkoff = Tinkoff.getInstance();
        Credit credit1 = new Credit(tinkoff, 650d, 18, Credit.Types.GOODS, false, true);
        Credit credit2 = new Credit(tinkoff, 660d, 23, Credit.Types.GOODS, false, true);
        Credit credit3 = new Credit(tinkoff, 450d, 11, Credit.Types.CAR, false, true);
        Credit credit4 = new Credit(tinkoff, 270d, 35, Credit.Types.OTHER, false, false);
        Credit credit5 = new Credit(tinkoff, 290d, 22, Credit.Types.OTHER, true, false);
        tinkoff.addCredit(credit1);
        tinkoff.addCredit(credit2);
        tinkoff.addCredit(credit3);
        tinkoff.addCredit(credit4);
        tinkoff.addCredit(credit5);
    }
}
