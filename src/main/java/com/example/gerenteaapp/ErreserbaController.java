package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ErreserbaController extends BaseController{

    @FXML
    private void gehitu() {
        try{
            FXMLLoader load11 = new FXMLLoader(getClass().getResource("gehituErreserbaView.fxml"));
            Parent root = load11.load();
            GehituErreserbaController gec = load11.getController();
            gec.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Mahaia gehitu");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void itxi() {
        try{

            FXMLLoader load2 = new FXMLLoader(getClass().getResource("menuView.fxml"));
            Parent root = load2.load();
            MenuController mnc = load2.getController();
            mnc.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Menua");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
