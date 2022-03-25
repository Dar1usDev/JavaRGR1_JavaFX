package Control;

import Model.Banks.Sberbank;
import Model.Banks.Tinkoff;
import Model.Banks.VTB;
import Model.Credit;
import Model.CreditTableView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Controller for main-view
 */
public class MainController implements Initializable {

    public HBox filtersHBox;
    public HBox creditsHBox;

    public TextField minSumTextbox;
    public TextField maxSumTextbox;

    public RadioButton tinkoffBankRadioButton;
    public RadioButton sberBankRadioButton;
    public RadioButton vtbBankRadioButton;

    public RadioButton earlyRepaymentRadioButton;
    public RadioButton prolongationRadioButton;
    public TableView<CreditTableView> tb;

    public RadioButton typeRadioButton1;
    public RadioButton typeRadioButton2;
    public RadioButton typeRadioButton3;
    public RadioButton typeRadioButton4;

    public ObservableList<CreditTableView> allCredits;
    public Button pickButton;
    private CreditTableView chosenLine;

    /**
     * Execute when interface starts
     *
     * @param url            idk
     * @param resourceBundle idk
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setInitialInterface();
        readData();
        showCredits();
    }

    /**
     * Sets initial interface params
     */
    private void setInitialInterface() {
        tinkoffBankRadioButton.fire();
        sberBankRadioButton.fire();
        vtbBankRadioButton.fire();
        typeRadioButton1.fire();
        typeRadioButton2.fire();
        typeRadioButton3.fire();
        typeRadioButton4.fire();
    }

    /**
     * Reads data from json
     */
    private void readData() {
        Sberbank.getInstance().readJSON();
        Tinkoff.getInstance().readJSON();
        VTB.getInstance().readJSON();
        allCredits = FXCollections.observableArrayList();

        allCredits.addAll(Sberbank.getInstance().getCreditOffers().stream().map((x) ->
                new CreditTableView(x.getBank(), x.getSum(), x.getTerm(),
                        x.getType(), x.isEarlyRepayment(), x.isProlongation())).toList());

        allCredits.addAll(Tinkoff.getInstance().getCreditOffers().stream().map((x) ->
                new CreditTableView(x.getBank(), x.getSum(), x.getTerm(),
                        x.getType(), x.isEarlyRepayment(), x.isProlongation())).toList());
        allCredits.addAll(VTB.getInstance().getCreditOffers().stream().map((x) ->
                new CreditTableView(x.getBank(), x.getSum(), x.getTerm(),
                        x.getType(), x.isEarlyRepayment(), x.isProlongation())).toList());

        Log.write(allCredits.get(0).getBank() + " " + allCredits.get(0).getSum());

        Log.write("AllCredits contains " + allCredits.size() + " credits");
    }

    /**
     * Creates and writes mock data
     */
    private void mockAndWrite() {
        Sberbank sberbank = Sberbank.getInstance();
        sberbank.addMockData();
        sberbank.writeJSON();

        Tinkoff tinkoff = Tinkoff.getInstance();
        tinkoff.addMockData();
        tinkoff.writeJSON();

        VTB vtb = VTB.getInstance();
        vtb.addMockData();
        vtb.writeJSON();
    }

    /**
     * Checks min sum value
     *
     * @param keyEvent idk
     */
    public void minSumChanged(KeyEvent keyEvent) {
        try {
            Integer.parseInt(minSumTextbox.getText());
        } catch (NumberFormatException e) {
            minSumTextbox.setText(String.valueOf(200000));
        }
        showCredits();
    }

    /**
     * Checks max sum value
     *
     * @param keyEvent idk
     */
    public void maxSumChanged(KeyEvent keyEvent) {
        try {
            Integer.parseInt(maxSumTextbox.getText());
        } catch (NumberFormatException e) {
            maxSumTextbox.setText(String.valueOf(1000000));
        }
        showCredits();
    }

    /**
     * Execute a method when any of radio button is changed
     *
     * @param actionEvent idk
     */
    public void radioButtonChanged(ActionEvent actionEvent) {
        showCredits();
    }

    /**
     * Visualize all data
     */
    public void showCredits() {

        if (allCredits == null)
            return;

        Log.write("aue " + allCredits.size());

        ObservableList<CreditTableView> pickedCredits = FXCollections.observableArrayList();

        Log.write("pressed " + tinkoffBankRadioButton.isSelected());

        //
        if (tinkoffBankRadioButton.isSelected()) {
            pickedCredits.addAll(allCredits.stream().filter((x) -> Objects.equals(x.getBank(), "Tinkoff")).toList());
        }

        if (vtbBankRadioButton.isSelected()) {
            pickedCredits.addAll(allCredits.stream().filter((x) -> Objects.equals(x.getBank(), "VTB")).toList());
        }

        if (sberBankRadioButton.isSelected()) {
            pickedCredits.addAll(allCredits.stream().filter((x) -> Objects.equals(x.getBank(), "Sberbank")).toList());
        }
        //
        if (earlyRepaymentRadioButton.isSelected()) {
            pickedCredits = FXCollections.observableArrayList(pickedCredits.stream().filter(CreditTableView::getEarlyrepayment).toList());
        }
        //
        if (prolongationRadioButton.isSelected()) {
            pickedCredits = FXCollections.observableArrayList(pickedCredits.stream().filter(CreditTableView::isProlongation).toList());
        }
        //
        pickedCredits = FXCollections.observableArrayList(pickedCredits.stream().filter((x) -> x.getSum() >= Double.parseDouble(minSumTextbox.getText()) &&
                x.getSum() <= Double.parseDouble(maxSumTextbox.getText())).toList());
        //
        ObservableList<CreditTableView> pickedCreditsTemp = pickedCredits;

        pickedCredits = FXCollections.observableArrayList();

        if (typeRadioButton1.isSelected()){
            pickedCredits.addAll(pickedCreditsTemp.stream().filter(((x) -> Objects.equals(x.getType(), Credit.Types.CAR.toString()))).toList());
        }

        if (typeRadioButton2.isSelected()){
            pickedCredits.addAll(pickedCreditsTemp.stream().filter(((x) -> Objects.equals(x.getType(), Credit.Types.GOODS.toString()))).toList());
        }

        if (typeRadioButton3.isSelected()){
            pickedCredits.addAll(pickedCreditsTemp.stream().filter(((x) -> Objects.equals(x.getType(), Credit.Types.FLAT.toString()))).toList());
        }

        if (typeRadioButton4.isSelected()){
            pickedCredits.addAll(pickedCreditsTemp.stream().filter(((x) -> Objects.equals(x.getType(), Credit.Types.OTHER.toString()))).toList());
        }
        //
        tb = new TableView<>(pickedCredits);

        List<String> columnsTitles = Arrays.asList("Bank", "Sum", "Term", "Type", "Earlyrepayment", "Prolongation");

        List<TableColumn<CreditTableView, String>> columns = new ArrayList<>();

        for (String columnsTitle : columnsTitles) {
            columns.add(new TableColumn<>(columnsTitle));
            columns.get(columns.size() - 1).setMinWidth(160d);
            columns.get(columns.size() - 1).setCellValueFactory(new PropertyValueFactory<>(columnsTitle.toLowerCase()));
            tb.getColumns().add(columns.get(columns.size() - 1));
        }

        tb.prefWidth(1150d);

        creditsHBox.getChildren().clear();
        creditsHBox.getChildren().add(tb);

        TableView.TableViewSelectionModel<CreditTableView> selectionModel = tb.getSelectionModel();

        selectionModel.selectedItemProperty().addListener((observableValue, creditTableView, t1) -> {
            if(t1 != null) {
                chosenLine = t1;
            }
        });

    }

    public void pickButtonClicked(MouseEvent mouseEvent) {
        Alert alert;
        if(chosenLine == null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ОШИБКА");
            alert.setHeaderText("Кредит не выбран");
        } else{
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Кредит выбран");
            alert.setHeaderText("Выбранный кредит");
            alert.setContentText("Банк: " + chosenLine.getBank() + ". Сумма: " + chosenLine.getSum() + ". Тип: " + chosenLine.getType()
                    + ". Срок: " + chosenLine.getTerm());
        }
        alert.showAndWait();
    }
}