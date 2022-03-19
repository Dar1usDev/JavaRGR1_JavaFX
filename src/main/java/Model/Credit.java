package Model;

import Model.Banks.Sberbank;
import Model.Banks.Tinkoff;
import Model.Banks.VTB;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Basic class for credits
 */
@JsonAdapter(Credit.CreditJsonAdapter.class)
public class Credit {

    /**
     * All credit types
     */
    public enum Types {
        FLAT, CAR, GOODS, OTHER
    }

    /**
     * Bank name
     */
    private Bank bank;

    /**
     * Basic credit sum
     */
    private double sum;

    /**
     * Credit length in days
     */
    private int term;

    /**
     * Type of credit
     */
    private Types type;

    /**
     * True if early repayment is available
     */
    private boolean earlyRepayment;

    /**
     * True if prolongation is available
     */
    private boolean prolongation;

    /**
     * Default constructor
     */
    public Credit() {

    }

    /**
     * Constructor w\ all params
     * @param bank           Bank name
     * @param sum            Basic credit sum
     * @param term           Credit length in days
     * @param type           Type of credit
     * @param earlyRepayment True if early repayment is available
     * @param prolongation   True if prolongation is available
     */
    public Credit(Bank bank, double sum, int term, Types type, boolean earlyRepayment, boolean prolongation) {
        this.bank = bank;
        this.sum = sum;
        this.term = term;
        this.type = type;
        this.earlyRepayment = earlyRepayment;
        this.prolongation = prolongation;
    }

    public Credit(String bank, double sum, int term, Types type, boolean earlyRepayment, boolean prolongation) {
        switch (bank){
            case "Tinkoff" -> this.bank = Tinkoff.getInstance();
            case "Sberbank" -> this.bank = Sberbank.getInstance();
            case "VTB" -> this.bank = VTB.getInstance();
        }
        this.sum = sum;
        this.term = term;
        this.type = type;
        this.earlyRepayment = earlyRepayment;
        this.prolongation = prolongation;
    }

    /**
     * Convert Credit object to a string
     *
     * @return 213
     */
    public String toString() {

        return " ";
    }

    public Bank getBank() {
        return bank;
    }

    public double getSum() {
        return sum;
    }

    public int getTerm() {
        return term;
    }

    public Types getType() {
        return type;
    }

    public boolean isEarlyRepayment() {
        return earlyRepayment;
    }

    public boolean isProlongation() {
        return prolongation;
    }

    public class CreditJsonAdapter extends TypeAdapter<Credit> {
        @Override
        public void write(JsonWriter out, Credit credit) throws IOException {
            out.beginObject();
            out.name("bank");
            out.value(credit.bank.name);
            out.name("sum");
            out.value(credit.sum);
            out.name("term");
            out.value(credit.term);
            out.name("type");
            out.value(String.valueOf(credit.type));
            out.name("earlyRepayment");
            out.value(credit.earlyRepayment);
            out.name("prolongation");
            out.value(credit.prolongation);
            out.endObject();
        }
        @Override
        public Credit read(JsonReader in) throws IOException {
            in.beginObject();
            String temp = in.nextName();
            String bank = in.nextString();
            temp = in.nextName();
            double sum= in.nextDouble();
            temp = in.nextName();
            int term= in.nextInt();
            temp = in.nextName();
            Types type = null;
            switch (in.nextString()) {
                case "FLAT" -> type = Types.FLAT;
                case "CAR" -> type = Types.CAR;
                case "GOODS" -> type = Types.GOODS;
                case "OTHER" -> type = Types.OTHER;
            }
            temp = in.nextName();
            boolean earlyRepayment = in.nextBoolean();
            temp = in.nextName();
            boolean prolongation = in.nextBoolean();
            in.endObject();
            return new Credit(bank, sum, term, type, earlyRepayment, prolongation);
        }
    }

}
