package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Clase Mahaiak
public class MahaiKudeaketa {

    private String id;
    private String numeroMesa;
    private String komensal;

    public MahaiKudeaketa(String id, String numeroMesa, String komensal) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.komensal = komensal;
    }

    public MahaiKudeaketa() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getKomensal() {
        return komensal;
    }

    public void setKomensal(String komensal) {
        this.komensal = komensal;
    }

    public ObservableList<MahaiKudeaketa> getMahaiak() {
        ObservableList<MahaiKudeaketa> mahaiakList = FXCollections.observableArrayList();

        try {
            // Conexión a la base de datos
            Connection conn = DBconexion.getConnection();

            String SQL = "SELECT * FROM mahaia";
            Statement stmt = conn.createStatement();
            ResultSet resul = stmt.executeQuery(SQL);

            while (resul.next()) {
                String id = resul.getString("id");
                String numMesa = resul.getString("numeroMesa");
                String komensal = resul.getString("komensal");

                MahaiKudeaketa m = new MahaiKudeaketa(id, numMesa, komensal);
                mahaiakList.add(m);
            }

            stmt.close();
            resul.close();

        } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
        }
        return mahaiakList;
    }
}
