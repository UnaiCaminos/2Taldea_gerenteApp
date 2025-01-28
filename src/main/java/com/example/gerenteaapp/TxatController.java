package com.example.gerenteaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TxatController extends BaseController {

    @FXML
    Label lblUser; // Etiqueta que muestra el nombre del usuario
    @FXML
    private TextArea txata; // Área de texto donde se muestran los mensajes
    @FXML
    private TextField mezuaField; // Campo para ingresar el mensaje

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in; // Flujo de entrada para leer mensajes del servidor

    // Método para establecer el nombre del usuario
    public String setErabiltzailea(String izena) {
        lblUser.setText(izena);
        return izena;
    }

    // Método para inicializar la conexión con el servidor
    public void initialize() {
        try {
            socket = new Socket("localhost", 5555);  // Establecer la conexión con el servidor en el puerto 5555
            out = new PrintWriter(socket.getOutputStream(), true);  // Se configura el output stream
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  // Flujo de entrada

            // Crear un hilo para escuchar los mensajes del servidor
            Thread listenerThread = new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = in.readLine()) != null) {
                        // Añadir el mensaje recibido al área de texto (interfaz de usuario)
                        if (mensaje != null && !mensaje.isEmpty()) {
                            txata.appendText(mensaje + "\n");
                        }
                    }
                } catch (IOException e) {
                    txata.appendText("Error al recibir mensaje: " + e.getMessage() + "\n");
                }
            });
            listenerThread.setDaemon(true);  // Para que se cierre cuando termine la aplicación
            listenerThread.start();  // Iniciar el hilo para recibir mensajes

        } catch (IOException e) {
            txata.appendText("Error al conectar con el servidor: " + e.getMessage() + "\n");
        }
    }

    // Método para enviar el mensaje al servidor
    @FXML
    private void bidaliMezua() {
        String mezua = mezuaField.getText().trim();

        // Verifica si el mensaje no está vacío antes de enviarlo
        if (!mezua.isEmpty()) {
            String usuario = lblUser.getText();  // Obtener el nombre de usuario de la etiqueta lblUser

            // Enviar el mensaje al servidor con el formato "Usuario: mensaje"
            out.println(usuario + ": " + mezua);

            // Agregar el mensaje al área de texto en el formato "Usuario: mensaje" y separarlo como un párrafo
            txata.appendText(usuario + ": " + mezua + "\n\n"); // '\n\n' agrega un salto de línea doble para separar los mensajes

            mezuaField.clear(); // Limpiar el campo de texto después de enviar el mensaje
        } else {
            txata.appendText("Ezin da mezua hutsik bidali.\n");  // Si el mensaje está vacío, mostrar un error
        }
    }

    // Método para cerrar la conexión y terminar la aplicación
    @FXML
    private void itxiKonexioa() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();  // Cerrar la conexión del socket si está abierta
            }
            System.exit(0);  // Terminar la aplicación
        } catch (IOException e) {
            txata.appendText("Error al cerrar la conexión: " + e.getMessage() + "\n");
        }
    }

    // Método para navegar al menú y cerrar la ventana actual
    @FXML
    private void itxi() {
        try {

            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("menuView.fxml"));
            Parent root = loader2.load();
            MenuController mc = loader2.getController();
            mc.setStage(usingStage);
            String izena = lblUser.getText();
            mc.setErabiltzailea(izena);

            usingStage.centerOnScreen();
            usingStage.setScene(new Scene(root));
            usingStage.setTitle("Menu");
            usingStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
