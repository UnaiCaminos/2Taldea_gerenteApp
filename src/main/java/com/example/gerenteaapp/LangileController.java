package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LangileController extends BaseController {
    @FXML
    private void gehitu() {
        try{

            FXMLLoader loada = new FXMLLoader(getClass().getResource("gehituLangileaView.fxml"));
            Parent root = loada.load();
            GehituLangileController glc = loada.getController();
            glc.setStage(usingStage);

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

            FXMLLoader loadx = new FXMLLoader(getClass().getResource("menuView.fxml"));
            Parent root = loadx.load();
            MenuController mnc = loadx.getController();
            mnc.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Menua");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
