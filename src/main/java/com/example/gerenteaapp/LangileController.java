package com.example.gerenteaapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;

public class LangileController extends BaseController {

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
    private TableColumn<Langilea, Boolean> columnTxatBaimena;

    @FXML
    private ObservableList<Langilea> items;

    @FXML
    private void initialize() throws SQLException {

        this.columnDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        this.columnIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.columnAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        this.columnPasahitza.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        this.columnKorreoa.setCellValueFactory(new PropertyValueFactory<>("korreoa"));
        this.columnTelefonoa.setCellValueFactory(new PropertyValueFactory<>("telefonoa"));
        this.columnPostua.setCellValueFactory(new PropertyValueFactory<>("postua"));

        items = com.example.gerenteaapp.LangileKudeaketa.langileaLortu();
        this.tabla.setItems(items);
    }

    @FXML
    private void gehitu() {
        try{

            FXMLLoader loada = new FXMLLoader(getClass().getResource("gehituLangileaView.fxml"));
            Parent root = loada.load();
            GehituLangileController glc = loada.getController();
            glc.setStage(usingStage);

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

            FXMLLoader loadx = new FXMLLoader(getClass().getResource("menuView.fxml"));
            Parent root = loadx.load();
            MenuController mnc = loadx.getController();
            mnc.setStage(usingStage);

            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Menua");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
