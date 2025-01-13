package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends BaseController {

    @FXML
    private void langileKudeaketa(){
        try {

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("langileView.fxml"));
            Parent root = loader1.load();
            LangileController lc = loader1.getController();
            lc.setStage(usingStage);

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

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserba kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void izena(){


    }
    @FXML
    private void saioaItxi(){
        try {

            FXMLLoader loader4 = new FXMLLoader(getClass().getResource("inicioSesionView.fxml"));
            Parent root = loader4.load();
            InicioSesionController ic = loader4.getController();
            ic.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Saioa hasi");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
