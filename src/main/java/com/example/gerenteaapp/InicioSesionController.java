package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioSesionController extends BaseController{

    @FXML
    private TextField userText;
    @FXML
    private PasswordField pwdText;

    @FXML
    private void sesioaHasi(){
        String user = userText.getText();
        String pwd = pwdText.getText();

        if (user.isEmpty() || pwd.isEmpty()) {
            mostrarAlerta(AlertType.ERROR, "Arazoa", "Eremuak hutsik", "Mesedez, bete eremu guztiak.");
            return;
        }

        LangileKudeaketa lk = new LangileKudeaketa();
        boolean esValido = lk.balioztatu(user, pwd);

        if (esValido) {
            mostrarAlerta(AlertType.INFORMATION, "Arrakasta", "Saioa ondo hasi da", "Ongi etorri, " + user + "!");
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("menuView.fxml"));
                Parent root = loader.load();
                MenuController mc = loader.getController();
                mc.setStage(usingStage);

                usingStage.setScene(new Scene(root));
                usingStage.setTitle("Menua");
                usingStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            mostrarAlerta(AlertType.ERROR, "Arazoa", "Kredentzialak ez dira zuzenak", "Erabiltzailea edo pwd okerra.");
        }
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String cabecera, String contenido) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
