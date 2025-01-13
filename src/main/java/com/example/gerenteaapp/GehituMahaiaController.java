package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GehituMahaiaController extends BaseController {

    @FXML
    private TextField txtMahaiZenb;
    @FXML
    private TextField txtKopurua;
    @FXML
    private void gehitu(){

            String mahaiZenb = txtMahaiZenb.getText();
            String kopurua = txtKopurua.getText();

            if (mahaiZenb.isEmpty() || kopurua.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Gune guztiak bete behar dira.");
                return;
            }

            if (isValidNum(mahaiZenb)||isValidNum(pertsonaKop)) {
               showAlert(Alert.AlertType.ERROR, "Error", "Mahai zenbakia eta pertsona kopuruan ezin da letrarik egon.");
            }

            try {
                int kop = Integer.parseInt(kopurua);
                MahaiKudeaketa m = new MahaiKudeaketa();
                m.mahaiaGehitu(mahaiZenb, kop);
                showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen sartu dira.");

            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Komentsal kopurua zenbakia izan behar da.");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Arazoa datuak sartzerakoan: " + e.getMessage());
            }
            try{
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
    private boolean isValidNum(String zenb) {
        String zenbRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
        return zenb.matches(zenbRegex);
    }
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
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
