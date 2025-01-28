package com.example.gerenteaapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;

public class ErreserbaAldatuController extends BaseController {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtIzena;
    @FXML
    private DatePicker dpData;
    @FXML
    private TextField txtMahaiZenb;
    @FXML
    private TextField txtPertsonaKop;
    @FXML
    private TextField txtKantzelatuta;
    @FXML
    private TableView<Erreserba> tabla;
    @FXML
    private TableColumn<Erreserba, String> columnId;
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

    String setErabiltzailea(String izena){
        lblUser.setText(izena);
        return izena;
    }

    @FXML
    private void initialize() throws SQLException {

        this.columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.numMesaColumn.setCellValueFactory(new PropertyValueFactory<>("mahaiZenbakia"));
        this.dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        this.pertsonaKopColumn.setCellValueFactory(new PropertyValueFactory<>("pertsonaKop"));
        this.kantzelatuaColumn.setCellValueFactory(new PropertyValueFactory<>("kantzelatuta"));
        this.updateByColumn.setCellValueFactory(new PropertyValueFactory<>("updateBy"));
        this.updateDataColumn.setCellValueFactory(new PropertyValueFactory<>("updateData"));

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
    private void aldatu() {
        String Id = txtId.getText();
        String izena = txtIzena.getText();
        LocalDate data = dpData.getValue();
        String pertsonaKop = txtPertsonaKop.getText();
        String kantzelatuta = txtKantzelatuta.getText();
        String numMesa = txtMahaiZenb.getText();

        if (Id.isEmpty()||izena.isEmpty()||pertsonaKop.isEmpty()||numMesa.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gune guztiak bete behar dira.");
            return;
        }

        boolean cancel = false;

        if (!kantzelatuta.isEmpty()) {
            cancel = true;
        }else{
            cancel = false;
        }

        if (isValidNum(numMesa)||isValidNum(pertsonaKop)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Mahai zenbakia eta pertsona kopuruan ezin da letrarik egon.");
        }

        LocalDate timestamp = (LocalDate.now());
        String updateBy = setErabiltzailea(lblUser.getText());

        int id = Integer.parseInt(Id);
        int kop = Integer.parseInt(pertsonaKop);
        int zenb = Integer.parseInt(numMesa);
        Date fecha = Date.valueOf(data);
        Date updateDate = Date.valueOf(timestamp);

        ErreserbaKudeaketa m = new ErreserbaKudeaketa();

        try {
            m.erreserbaAldatu(izena, zenb, fecha, kop, cancel ,updateBy, updateDate, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen aldatu dira.");

        try{
            FXMLLoader xload = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = xload.load();
            ErreserbaController mhc = xload.getController();
            mhc.setStage(usingStage);
            String name = lblUser.getText();
            mhc.setErabiltzailea(name);

            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Erreserben kudeaketa");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isValidNum(String zenb) {
        String zenbRegex = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";
        return zenb.matches(zenbRegex);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
