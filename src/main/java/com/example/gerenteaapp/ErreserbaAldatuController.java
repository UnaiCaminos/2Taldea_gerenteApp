package com.example.gerenteaapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class ErreserbaAldatuController extends BaseController {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtIzena;
    @FXML
    private TextField txtData;
    @FXML
    private TextField txtMahaiZenb;
    @FXML
    private TextField txtPertsonaKop;
    @FXML
    private TextField txtKantzelatuta;
    @FXML
    private TableView<Erreserba> tabla;
    @FXML
    private TableColumn<Erreserba, String> idColumn;
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
    private void initialize() throws SQLException {

        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.numMesaColumn.setCellValueFactory(new PropertyValueFactory<>("mahaiZenbakia"));
        this.dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        this.pertsonaKopColumn.setCellValueFactory(new PropertyValueFactory<>("pertsonaKop"));
        this.kantzelatuaColumn.setCellValueFactory(new PropertyValueFactory<>("kantzelatuta"));
        this.updateDataColumn.setCellValueFactory(new PropertyValueFactory<>("updateData"));
        this.updateByColumn.setCellValueFactory(new PropertyValueFactory<>("updateBy"));

        items = ErreserbaKudeaketa.erreserbaLortu();
        this.tabla.setItems(items);
    }

    @FXML
    private void itxi() {
        try{

            FXMLLoader load2 = new FXMLLoader(getClass().getResource("erreserbaView.fxml"));
            Parent root = load2.load();
            ErreserbaController ec = load2.getController();
            ec.setStage(usingStage);

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
        String data = txtData.getText();
        String pertsonaKop = txtPertsonaKop.getText();
        String kantzelatuta = txtKantzelatuta.getText();
        String numMesa = txtMahaiZenb.getText();

        if (Id.isEmpty()) {
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

        if (isValidDate(data)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Dataren formatua URTEA-HILABETEA-EGUNA izan behar da.");
        }
        Calendar calendar = Calendar.getInstance();

        java.util.Date updateDate = calendar.getTime();

        String updateBy = "Jonan";
        try {
            int id = Integer.parseInt(Id);
            int kop = Integer.parseInt(pertsonaKop);
            int zenb = Integer.parseInt(numMesa);
            Date fecha = Date.valueOf(data);

            ErreserbaKudeaketa m = new ErreserbaKudeaketa();
            m.erreserbaAldatu(izena, zenb, fecha, kop, cancel, updateBy, id);
            showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen aldatu dira.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            FXMLLoader xload = new FXMLLoader(getClass().getResource("erreserbaAldatuView.fxml"));
            Parent root = xload.load();
            ErreserbaAldatuController mhc = xload.getController();
            mhc.setStage(usingStage);

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
    private boolean isValidDate(String date) {
        String dateRegex = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$";
        return date.matches(dateRegex);
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
