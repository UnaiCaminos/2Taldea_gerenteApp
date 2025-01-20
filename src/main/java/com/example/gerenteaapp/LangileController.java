package com.example.gerenteaapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private TableColumn<Langilea, String> columnUpdateData;
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

        this.columnDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        this.columnIzena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.columnAbizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        this.columnPasahitza.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        this.columnKorreoa.setCellValueFactory(new PropertyValueFactory<>("korreoa"));
        this.columnTelefonoa.setCellValueFactory(new PropertyValueFactory<>("telefonoa"));
        this.columnPostua.setCellValueFactory(new PropertyValueFactory<>("postua"));
        this.columnUpdateData.setCellValueFactory(new PropertyValueFactory<>("updateData"));
        this.columnUpdateBy.setCellValueFactory(new PropertyValueFactory<>("updateBy"));

        items = LangileKudeaketa.langileaLortu();
        this.tabla.setItems(items);
        setErabiltzailea(lblUser.getText());
    }

    @FXML
    private void gehitu() {
        try{

            FXMLLoader loada = new FXMLLoader(getClass().getResource("gehituLangileaView.fxml"));
            Parent root = loada.load();
            GehituLangileController glc = loada.getController();
            glc.setStage(usingStage);
            String izena = lblUser.getText();
            glc.setErabiltzailea(izena);

            usingStage.centerOnScreen();
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

            FXMLLoader loadx = new FXMLLoader(getClass().getResource("langileaKenduView.fxml"));
            Parent root = loadx.load();
            LangileaKenduController mnc = loadx.getController();
            mnc.setStage(usingStage);
            String izena = lblUser.getText();
            mnc.setErabiltzailea(izena);

            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Langilea Kendu");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aldatu() {
        try{

            FXMLLoader loadx = new FXMLLoader(getClass().getResource("langileaAldatuView.fxml"));
            Parent root = loadx.load();
            LangileaAldatuController mnc = loadx.getController();
            mnc.setStage(usingStage);
            String izena = lblUser.getText();
            mnc.setErabiltzailea(izena);


            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Langilea Aldatu");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
