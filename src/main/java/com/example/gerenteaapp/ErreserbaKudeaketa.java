package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ErreserbaKudeaketa {
        public static ObservableList<Erreserba> erreserbaLortu() throws SQLException {
            ObservableList<Erreserba> lista = FXCollections.observableArrayList();

            String query = "SELECT * FROM dberronka.erreserba";
            try (Connection conn = DBconexion.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    lista.add(new Erreserba(
                            rs.getInt("id"),
                            rs.getString("izena"),
                            rs.getInt("mahaiZenbakia"),
                            rs.getDate("data"),
                            rs.getInt("pertsonaKop"),
                            rs.getBoolean("kantzelatuta"),
                            rs.getDate("updateData"),
                            rs.getString("updateBy")
                    ));
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Error de conexión a la base de datos", e);
            }

            return lista;
        }


}
