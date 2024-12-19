package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class GehituErreserbaController extends BaseController {
    @FXML
    private void itxi() {
        try {

            FXMLLoader load0 = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = load0.load();
            ErreserbaController ec = load0.getController();
            ec.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Mahaien kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
