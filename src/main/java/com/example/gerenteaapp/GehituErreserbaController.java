package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class GehituErreserbaController extends BaseController {
    @FXML
    private TextField txtIzena;
    @FXML
    private TextField txtMahaiZenb;
    @FXML
    private DatePicker dpData;
    @FXML
    private TextField txtPertsonaKop;
    @FXML
    Label lblUser;

    void setErabiltzailea(String izena){
        lblUser.setText(izena);
    }
    @FXML
    private void initialize() {
        setErabiltzailea(lblUser.getText());
    }
    @FXML
    private void gehitu(){

        String erreserbaIzena = txtIzena.getText();
        String mahaiZenb = txtMahaiZenb.getText();
        LocalDate data = dpData.getValue();
        String pertsonaKop = txtPertsonaKop.getText();

        if (erreserbaIzena.isEmpty() || mahaiZenb.isEmpty() || pertsonaKop.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gune guztiak bete behar dira.");
            return;
        }

        if (isValidNum(mahaiZenb)||isValidNum(pertsonaKop)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Mahai zenbakia eta pertsona kopuruan ezin da letrarik egon.");
        }

        try {
            int zenb = Integer.parseInt(mahaiZenb);
            int kop = Integer.parseInt(pertsonaKop);
            Date fecha = Date.valueOf(data);
            ErreserbaKudeaketa e = new ErreserbaKudeaketa();
            e.erreserbaGehitu(erreserbaIzena, zenb, fecha, kop);
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
            String izena = lblUser.getText();
            ec.setErabiltzailea(izena);

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
    @FXML
    private void itxi() {
        try {

            FXMLLoader load0 = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = load0.load();
            ErreserbaController ec = load0.getController();
            ec.setStage(usingStage);
            String izena = lblUser.getText();
            ec.setErabiltzailea(izena);

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
