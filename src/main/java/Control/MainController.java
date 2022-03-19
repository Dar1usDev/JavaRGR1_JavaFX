package Control;

import Model.Banks.Sberbank;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public HBox filtersHBox;
    public HBox creditsHBox;

    public ListView listView;

    public TextField minSumTextbox;
    public TextField maxSumTextbox;

    public RadioButton tinkoffBankRadioButton;
    public RadioButton sberBankRadioButton;
    public RadioButton vtbBankRadioButton;

    public RadioButton earlyRepaymentRadioButton;
    public RadioButton prolongationRadioButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tinkoffBankRadioButton.fire();
        sberBankRadioButton.fire();
        vtbBankRadioButton.fire();

        Sberbank sberbank = Sberbank.getInstance();
        sberbank.addMockData();
        sberbank.writeJSON();
    }
}