package com.example.gerenteaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GerenteApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GerenteApp.class.getResource("inicioSesionView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Saioa hasi");
        stage.setScene(scene);
        stage.show();

        stage.setWidth(javafx.stage.Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(javafx.stage.Screen.getPrimary().getVisualBounds().getHeight());

        InicioSesionController isc = fxmlLoader.getController();
        isc.setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}