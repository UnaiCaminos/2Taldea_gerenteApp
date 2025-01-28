package com.example.gerenteaapp;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class LangileaAldatuController extends BaseController {
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtIzena;
    @FXML
    private TextField txtAbizena;
    @FXML
    private TextField txtKorreoa;
    @FXML
    private TextField txtTelefonoa;
    @FXML
    private TextField txtPasahitza;
    @FXML
    private TextField txtTxatBaimena;
    @FXML
    private ComboBox lanPostuaComboBox;
    @FXML
    private TableView<Langilea> tabla;
    @FXML
    private TableColumn<Langilea, Integer> idColumn;
    @FXML
    private TableColumn<Langilea, String> dniColumn;
    @FXML
    private TableColumn<Langilea, String> izenaColumn;
    @FXML
    private TableColumn<Langilea, String> abizenaColumn;
    @FXML
    private TableColumn<Langilea, String> pasahitzaColumn;
    @FXML
    private TableColumn<Langilea, String> korreoaColumn;
    @FXML
    private TableColumn<Langilea, String> telefonoaColumn;
    @FXML
    private TableColumn<Langilea, String> postuaColumn;
    @FXML
    private TableColumn<Langilea, String> txatBaimenaColumn;
    @FXML
    private TableColumn<Langilea, String> columnUpdateBy;
    @FXML
    private TableColumn<Langilea, String> columnUpdateData;
    @FXML
    private ObservableList<Langilea> items;
    @FXML
    Label lblUser;

    String setErabiltzailea(String izena){
        lblUser.setText(izena);
        return izena;
    }

    @FXML
    private void initialize() throws SQLException {

        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
        this.izenaColumn.setCellValueFactory(new PropertyValueFactory<>("izena"));
        this.abizenaColumn.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        this.pasahitzaColumn.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        this.korreoaColumn.setCellValueFactory(new PropertyValueFactory<>("korreoa"));
        this.telefonoaColumn.setCellValueFactory(new PropertyValueFactory<>("telefonoa"));
        this.postuaColumn.setCellValueFactory(new PropertyValueFactory<>("postua"));
        this.txatBaimenaColumn.setCellValueFactory(new PropertyValueFactory<>("txatBaimena"));
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
            usingStage.setTitle("Erreserba");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void aldatu() {
        String Id = txtId.getText();
        String langileIzena = txtIzena.getText();
        String abizena = txtAbizena.getText();
        String baimena = txtTxatBaimena.getText();
        String korreoa = txtKorreoa.getText();
        String pasahitza = txtPasahitza.getText();
        String telefonoa = txtTelefonoa.getText();
        String dni = txtDni.getText();
        String postua = lanPostuaComboBox.getValue().toString();

        if (Id.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gune guztiak bete behar dira.");
            return;
        }

        LocalDate timestamp = (LocalDate.now());
        String updateBy = setErabiltzailea(lblUser.getText());

        try {
            int id = Integer.parseInt(Id);
            Date updateData = Date.valueOf(timestamp);
            Boolean txatBaimena = Boolean.parseBoolean(baimena);

            LangileKudeaketa l = new LangileKudeaketa();
            l.langileaAldatu(dni, langileIzena, abizena, pasahitza, korreoa, telefonoa, postua, txatBaimena, updateData, updateBy, id);
            showAlert(Alert.AlertType.INFORMATION, "Ondo", "Datuak zuzen aldatu dira.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
