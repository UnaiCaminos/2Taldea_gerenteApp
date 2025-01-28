package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController extends BaseController {

    @FXML
    private Label lblUser;

    void setErabiltzailea(String izena){
        lblUser.setText(izena);
    }
    @FXML
    private void initialize() {
        setErabiltzailea(lblUser.getText());
    }
    @FXML
    private void langileKudeaketa(){
        try {
            String izena = lblUser.getText();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("langileView.fxml"));
            Parent root = loader1.load();
            LangileController lc = loader1.getController();
            lc.setStage(usingStage);
            lc.setErabiltzailea(izena);

            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Langile kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void mahaiKudeaketa(){
        try {

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("mahaiaView.fxml"));
            Parent root = loader2.load();
            MahaiaController mc = loader2.getController();
            mc.setStage(usingStage);
            String izena = lblUser.getText();
            mc.setErabiltzailea(izena);

            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Mahaien kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void erreserbaKudeaketa(){
        try {

            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = loader3.load();
            ErreserbaController ec = loader3.getController();
            ec.setStage(usingStage);
            String izena = lblUser.getText();
            ec.setErabiltzailea(izena);


            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserba kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void txatKudeaketa(){
        LangileKudeaketa lk = new LangileKudeaketa();
        boolean baimena = lk.baimenaTxat(lblUser.getText());
        if(baimena) {
            try {

                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("txatView.fxml"));
                Parent root = loader3.load();
                TxatController tc = loader3.getController();
                tc.setStage(usingStage);
                String izena = lblUser.getText();
                tc.setErabiltzailea(izena);


                usingStage.centerOnScreen();
                usingStage.setScene(new Scene(root));
                usingStage.setTitle("Txata");
                usingStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Arazoa", "Ez duzu baimenik txatean sartzeko.");
        }
    }
    @FXML
    private void saioaItxi(){
        try {

            FXMLLoader loader4 = new FXMLLoader(getClass().getResource("inicioSesionView.fxml"));
            Parent root = loader4.load();
            InicioSesionController ic = loader4.getController();
            ic.setStage(usingStage);

            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Saioa hasi");
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
