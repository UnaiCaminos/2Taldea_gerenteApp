package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GehituController extends BaseController {
    @FXML
    private void itxi() {
        try {

            FXMLLoader xload = new FXMLLoader(getClass().getResource("mahaiaView.fxml"));
            Parent root = xload.load();
            MahaiaController mhc = xload.getController();
            mhc.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Mahaien kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
