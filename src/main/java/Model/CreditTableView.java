package Model;

import javafx.beans.property.*;

/**
 * Class for data visualization
 */
public class CreditTableView {
    private SimpleStringProperty bank;
    private SimpleDoubleProperty sum;
    private SimpleIntegerProperty term;
    private SimpleStringProperty type;
    private SimpleBooleanProperty earlyrepayment;
    private SimpleBooleanProperty prolongation;

    public CreditTableView(Bank bank, double sum, int term, Credit.Types type,
                           boolean earlyRepayment, boolean prolongation){
        this.bank = new SimpleStringProperty(bank.name);
        this.sum = new SimpleDoubleProperty(sum);
        this.term = new SimpleIntegerProperty(term);
        this.type = new SimpleStringProperty(type.toString());
        this.earlyrepayment = new SimpleBooleanProperty(earlyRepayment);
        this.prolongation = new SimpleBooleanProperty(prolongation);
    }

    public String getBank() {
        return bank.get();
    }

    public SimpleStringProperty bankProperty() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank.set(bank);
    }

    public double getSum() {
        return sum.get();
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public int getTerm() {
        return term.get();
    }

    public SimpleIntegerProperty termProperty() {
        return term;
    }

    public void setTerm(int term) {
        this.term.set(term);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public boolean getEarlyrepayment() {
        return earlyrepayment.get();
    }

    public SimpleBooleanProperty earlyrepaymentProperty() {
        return earlyrepayment;
    }

    public void setEarlyrepayment(boolean earlyrepayment) {
        this.earlyrepayment.set(earlyrepayment);
    }

    public boolean isProlongation() {
        return prolongation.get();
    }

    public SimpleBooleanProperty prolongationProperty() {
        return prolongation;
    }

    public void setProlongation(boolean prolongation) {
        this.prolongation.set(prolongation);
    }
}
