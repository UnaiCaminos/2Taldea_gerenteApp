package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MahaiaController extends BaseController {

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
    private void initialize() throws SQLException {

        this.columId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columNumeroMesa.setCellValueFactory(new PropertyValueFactory<>("mahaiZenbakia"));
        this.columKomensal.setCellValueFactory(new PropertyValueFactory<>("kopurua"));

        items = MahaiKudeaketa.mahaiaLortu();
        this.tabla.setItems(items);
    }

    @FXML
    private void gehitu() {
        try{

            FXMLLoader fxmlLoad1 = new FXMLLoader(getClass().getResource("gehituView.fxml"));
            Parent root = fxmlLoad1.load();
            GehituController gc = fxmlLoad1.getController();
            gc.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Mahaia gehitu");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void itxi() {
        try{

            FXMLLoader fxmlLoad = new FXMLLoader(getClass().getResource("menuView.fxml"));
            Parent root = fxmlLoad.load();
            MenuController mnc = fxmlLoad.getController();
            mnc.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Menua");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void borratu() {}

    @FXML
    private void aktualizatu() {}


}
