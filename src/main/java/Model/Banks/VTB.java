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
 * Class for VTB
 */
public class VTB extends Bank implements IJSONIO, IMockData {

    /**
     * Singlton object
     */
    static VTB instance;

    /**
     * Singleton realization
     *
     * @return VTB instance
     */
    public static synchronized VTB getInstance() {
        if (instance == null) {
            instance = new VTB();
        }
        return instance;
    }

    public VTB() {
        this.name = "VTB";
        this.rate = 1.3d;
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
                String jsonData = Files.readString(Path.of("src//main//resources//db//vtbData.json"));
                VTB.getInstance().creditOffers = new Gson().fromJson(jsonData, new TypeToken<List<Credit>>() {
                }.getType());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Thread readThread = new Thread(readRunnable, "readThread");
        readThread.start();

        try {
            readThread.join();
        } catch (InterruptedException ignored) {
            // :)
        }

        Log.write(VTB.getInstance().creditOffers.size() + " objects were read for VTB");
    }

    @Override
    public void writeJSON() {
        Runnable readRunnable = () -> {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            try {
                Files.write(Path.of("src//main//resources//db//vtbData.json"), Collections.singleton(gson.toJson(VTB.getInstance().creditOffers)));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Thread writeThread = new Thread(readRunnable, "vtbWriteThread");
        writeThread.start();

        try {
            writeThread.join();
        } catch (InterruptedException ignored) {
            // :)
        }
    }

    @Override
    public void addMockData() {
        VTB vtb = VTB.getInstance();
        Credit credit1 = new Credit(vtb, 700d, 18, Credit.Types.FLAT, false, false);
        Credit credit2 = new Credit(vtb, 600d, 28, Credit.Types.GOODS, false, false);
        Credit credit3 = new Credit(vtb, 500d, 7, Credit.Types.CAR, false, true);
        Credit credit4 = new Credit(vtb, 300d, 45, Credit.Types.OTHER, true, true);
        Credit credit5 = new Credit(vtb, 260d, 23, Credit.Types.CAR, true, false);
        vtb.addCredit(credit1);
        vtb.addCredit(credit2);
        vtb.addCredit(credit3);
        vtb.addCredit(credit4);
        vtb.addCredit(credit5);
    }
}
