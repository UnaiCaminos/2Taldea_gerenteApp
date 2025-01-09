package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;

public class GehituErreserbaController extends BaseController {
    @FXML
    private TextField txtIzena;
    @FXML
    private TextField txtMahaiZenb;
    @FXML
    private TextField txtData;
    @FXML
    private TextField txtPertsonaKop;
    @FXML
    private void gehitu(){

        String izena = txtIzena.getText();
        String mahaiZenb = txtMahaiZenb.getText();
        String data = txtData.getText();
        String pertsonaKop = txtPertsonaKop.getText();

        if (izena.isEmpty() || mahaiZenb.isEmpty() || data.isEmpty() || pertsonaKop.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gune guztiak bete behar dira.");
            return;
        }

        try {
            int zenb = Integer.parseInt(mahaiZenb);
            int kop = Integer.parseInt(pertsonaKop);
            Date fecha = Date.valueOf(data);
            ErreserbaKudeaketa e = new ErreserbaKudeaketa();
            e.erreserbaGehitu(izena, zenb, fecha, kop);
            showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen sartu dira.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Komentsal kopurua zenbakia izan behar da.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Arazoa datuak sartzerakoan: " + e.getMessage());
        }
        try{
            FXMLLoader xload = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = xload.load();
            ErreserbaController ec = xload.getController();
            ec.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserben kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
