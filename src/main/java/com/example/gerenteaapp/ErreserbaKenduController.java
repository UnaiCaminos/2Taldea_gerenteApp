package com.example.gerenteaapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;

public class ErreserbaKenduController extends BaseController{
    @FXML
    private TextField txtId;
    @FXML
    private TableView<Erreserba> tabla;
    @FXML
    private TableColumn<Erreserba, Integer> columnId;
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

        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
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
    private void itxi() {
        try{

            FXMLLoader load2 = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = load2.load();
            ErreserbaController ec = load2.getController();
            ec.setStage(usingStage);
            String izena = lblUser.getText();
            ec.setErabiltzailea(izena);


            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserba");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void ezabatu() {
        String id = txtId.getText();

        if (id.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gune guztiak bete behar dira.");
            return;
        }

        try {
            int zenb = Integer.parseInt(id);
            ErreserbaKudeaketa m = new ErreserbaKudeaketa();
            m.erreserbaKendu(zenb);
            showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen borratu dira.");

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Id-a zenbakia izan behar da.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        try{
            FXMLLoader xload = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = xload.load();
            ErreserbaController mhc = xload.getController();
            mhc.setStage(usingStage);
            String izena = lblUser.getText();
            mhc.setErabiltzailea(izena);


            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserben kudeaketa");
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
