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


public class LangileaKenduController extends BaseController{

    @FXML
    private TextField txtId;
    @FXML
    private TableView<Langilea> tabla;
    @FXML
    private TableColumn<Langilea, Integer> columnId;
    @FXML
    private TableColumn<Langilea, String> columnDni;
    @FXML
    private TableColumn<Langilea, String> columnIzena;
    @FXML
    private TableColumn<Langilea, String> columnAbizena;
    @FXML
    private TableColumn<Langilea, String> columnPasahitza;
    @FXML
    private TableColumn<Langilea, String> columnKorreoa;
    @FXML
    private TableColumn<Langilea, String> columnTelefonoa;
    @FXML
    private TableColumn<Langilea, String> columnPostua;
    @FXML
    private TableColumn<Langilea, Data> columnUpdateData;
    @FXML
    private TableColumn<Langilea, String> columnUpdateBy;
    @FXML
    private ObservableList<Langilea> items;
    @FXML
    Label lblUser;

    void setErabiltzailea(String izena){
        lblUser.setText(izena);
    }
    @FXML
    private void initialize() throws SQLException {

        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        this.columnIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.columnAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        this.columnPasahitza.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        this.columnKorreoa.setCellValueFactory(new PropertyValueFactory<>("korreoa"));
        this.columnTelefonoa.setCellValueFactory(new PropertyValueFactory<>("telefonoa"));
        this.columnPostua.setCellValueFactory(new PropertyValueFactory<>("postua"));
        this.columnUpdateBy.setCellValueFactory(new PropertyValueFactory<>("updateBy"));
        this.columnUpdateData.setCellValueFactory(new PropertyValueFactory<>("updateData"));

        items = LangileKudeaketa.langileaLortu();
        this.tabla.setItems(items);
        setErabiltzailea(lblUser.getText());
    }

        @FXML
        private void itxi() {
            try{

                FXMLLoader load2 = new FXMLLoader(getClass().getResource("langileView.fxml"));
                Parent root = load2.load();
                LangileController ec = load2.getController();
                ec.setStage(usingStage);
                String izena = lblUser.getText();
                ec.setErabiltzailea(izena);

                usingStage.centerOnScreen();
                usingStage.setScene(new Scene(root));
                usingStage.setTitle("Langilea");
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
                LangileKudeaketa m = new LangileKudeaketa();
                m.langileaKendu(zenb);
                showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen borratu dira.");

            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Id-a zenbakia izan behar da.");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
            }
            try{
                FXMLLoader xload = new FXMLLoader(getClass().getResource("langileView.fxml"));
                Parent root = xload.load();
                LangileController mhc = xload.getController();
                mhc.setStage(usingStage);
                String izena = lblUser.getText();
                mhc.setErabiltzailea(izena);

                usingStage.centerOnScreen();
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
