module com.example.javargr1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.google.gson;

    opens com.example.javargr1 to javafx.fxml;
    exports com.example.javargr1;
    exports Control;
    opens Control to javafx.fxml;
    opens Model to javafx.base;
}