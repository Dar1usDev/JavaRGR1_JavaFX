package com.example.javargr1;

import Control.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("MainWindow");
        stage.setScene(scene);
        Log.write("Application started");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}