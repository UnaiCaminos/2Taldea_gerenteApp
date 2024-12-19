package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class GehituLangileController extends BaseController {
    @FXML
    private void itxi() {
        try {

            FXMLLoader fLoad = new FXMLLoader(getClass().getResource("langileView.fxml"));
            Parent root = fLoad.load();
            LangileController lc = fLoad.getController();
            lc.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Langileen kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
