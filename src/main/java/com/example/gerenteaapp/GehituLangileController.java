package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GehituLangileController extends BaseController {
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtIzena;
    @FXML
    private TextField txtAbizena;
    @FXML
    private TextField txtPasahitza;
    @FXML
    private TextField txtKorreoa;
    @FXML
    private TextField txtTelefonoa;
    @FXML
    private TextField txtPostua;
    @FXML
    private void gehitu(){

        String dni = txtDni.getText();
        String izena = txtIzena.getText();
        String abizena = txtAbizena.getText();
        String pasahitza = txtPasahitza.getText();
        String korreoa = txtKorreoa.getText();
        String telefonoa = txtTelefonoa.getText();
        String postua = txtPostua.getText();

        if (dni.isEmpty() || izena.isEmpty() || abizena.isEmpty() || pasahitza.isEmpty() || korreoa.isEmpty() || telefonoa.isEmpty() || postua.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gune guztiak bete behar dira.");
            return;
        }

        try {
            LangileKudeaketa l = new LangileKudeaketa();
            l.langileaGehitu(dni, izena, abizena, pasahitza, korreoa, telefonoa, postua);
            showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen sartu dira.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Komentsal kopurua zenbakia izan behar da.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Arazoa datuak sartzerakoan: " + e.getMessage());
        }
        try{
            FXMLLoader xload = new FXMLLoader(getClass().getResource("langileView.fxml"));
            Parent root = xload.load();
            LangileController lc = xload.getController();
            lc.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Langileen kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
