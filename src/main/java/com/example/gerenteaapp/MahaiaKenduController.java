package com.example.gerenteaapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class MahaiaKenduController extends BaseController {

    @FXML
    private TextField txtId;
    @FXML
    private TableView<Mahaia> tabla;
    @FXML
    private TableColumn<Mahaia, Integer> columId;
    @FXML
    private TableColumn<Mahaia, Integer> columNumeroMesa;
    @FXML
    private TableColumn<Mahaia, Integer> columKomensal;
    @FXML
    private ObservableList<Mahaia> items;

    @FXML
    private void itxi() {
        try{

            FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("menuView.fxml"));
            Parent root = fxmlLoad.load();
            MenuController mnc = fxmlLoad.getController();
            mnc.setStage(usingStage);

            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Menua");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void initialize() throws SQLException {

        this.columId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columNumeroMesa.setCellValueFactory(new PropertyValueFactory<>("mahaiZenbakia"));
        this.columKomensal.setCellValueFactory(new PropertyValueFactory<>("kopurua"));

        items = MahaiKudeaketa.mahaiaLortu();
        this.tabla.setItems(items);
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
            MahaiKudeaketa m = new MahaiKudeaketa();
            m.mahaiaKendu(zenb);
            showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen borratu dira.");

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Mahai zenbakia zenbakia izan behar da.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        try{
            FXMLLoader xload = new FXMLLoader(getClass().getResource("mahaiaView.fxml"));
            Parent root = xload.load();
            MahaiaController mhc = xload.getController();
            mhc.setStage(usingStage);

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
