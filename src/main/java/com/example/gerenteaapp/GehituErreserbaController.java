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

        if (isValidNum(mahaiZenb)||isValidNum(pertsonaKop)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Mahai zenbakia eta pertsona kopuruan ezin da letrarik egon.");
        }

        if (isValidDate(data)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Dataren formatua URTEA/HILABETEA/EGUNA izan behar da.");
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

            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserben kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isValidNum(String zenb) {
        String zenbRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
        return zenb.matches(zenbRegex);
    }
    private boolean isValidDate(String date) {
        String dateRegex = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$";
        return date.matches(dateRegex);
    }
    @FXML
    private void itxi() {
        try {

            FXMLLoader load0 = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = load0.load();
            ErreserbaController ec = load0.getController();
            ec.setStage(usingStage);

            usingStage.centerOnScreen();
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
