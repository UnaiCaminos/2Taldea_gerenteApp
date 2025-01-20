package com.example.gerenteaapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;

public class ErreserbaController extends BaseController{

    @FXML
    private TableView<Erreserba> tabla;
    @FXML
    private TableColumn<Erreserba, String> izenaColumn;
    @FXML
    private TableColumn<Erreserba, Integer> numMesaColumn;
    @FXML
    private TableColumn<Erreserba, Data> dataColumn;
    @FXML
    private TableColumn<Erreserba, Integer> pertsonaKopColumn;
    @FXML
    private TableColumn<Erreserba, Boolean> kantzelatuaColumn;
    @FXML
    private TableColumn<Erreserba, Data> updateDataColumn;
    @FXML
    private TableColumn<Erreserba, String> updateByColumn;
    @FXML
    private ObservableList<Erreserba> items;
    @FXML
    Label lblUser;

    void setErabiltzailea(String izena){
        lblUser.setText(izena);
    }
    @FXML
    private void initialize() throws SQLException {

        this.izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.numMesaColumn.setCellValueFactory(new PropertyValueFactory<>("mahaiZenbakia"));
        this.dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        this.pertsonaKopColumn.setCellValueFactory(new PropertyValueFactory<>("pertsonaKop"));
        this.kantzelatuaColumn.setCellValueFactory(new PropertyValueFactory<>("kantzelatuta"));
        this.updateDataColumn.setCellValueFactory(new PropertyValueFactory<>("updateData"));
        this.updateByColumn.setCellValueFactory(new PropertyValueFactory<>("updateBy"));

        items = ErreserbaKudeaketa.erreserbaLortu();
        this.tabla.setItems(items);
        setErabiltzailea(lblUser.getText());
    }
    @FXML
    private void gehitu() {
        try{
            FXMLLoader load11 = new FXMLLoader(getClass().getResource("gehituErreserbaView.fxml"));
            Parent root = load11.load();
            GehituErreserbaController gec = load11.getController();
            gec.setStage(usingStage);
            String izena = lblUser.getText();
            gec.setErabiltzailea(izena);


            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserba gehitu");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void itxi() {
        try{

            FXMLLoader load2 = new FXMLLoader(getClass().getResource("menuView.fxml"));
            Parent root = load2.load();
            MenuController mnc = load2.getController();
            mnc.setStage(usingStage);
            String izena = lblUser.getText();
            mnc.setErabiltzailea(izena);


            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Menua");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void kendu() {
        try{
            FXMLLoader load11 = new FXMLLoader(getClass().getResource("erreserbaKenduView.fxml"));
            Parent root = load11.load();
            ErreserbaKenduController gec = load11.getController();
            gec.setStage(usingStage);
            String izena = lblUser.getText();
            gec.setErabiltzailea(izena);


            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserba kendu");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aldatu() {
        try{
            FXMLLoader load11 = new FXMLLoader(getClass().getResource("erreserbaAldatuView.fxml"));
            Parent root = load11.load();
            ErreserbaAldatuController gec = load11.getController();
            gec.setStage(usingStage);
            String izena = lblUser.getText();
            gec.setErabiltzailea(izena);


            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserba aldatu");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
