package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LangileKudeaketa {
    public static ObservableList<Langilea> langileaLortu() throws SQLException {
        ObservableList<Langilea> lista = FXCollections.observableArrayList();

        String query = "SELECT * FROM dberronka.langilea";
        try (Connection conn = DBconexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lista.add(new Langilea(
                        rs.getInt("id"),
                        rs.getString("dni"),
                        rs.getString("izena"),
                        rs.getString("abizena"),
                        rs.getString("pasahitza"),
                        rs.getString("korreoa"),
                        rs.getString("telefonoa"),
                        rs.getString("postua"),
                        rs.getBoolean("txatBaimena")
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }

        return lista;
    }
}
