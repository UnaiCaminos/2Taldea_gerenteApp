package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ErreserbaKudeaketa {
        public static ObservableList<Erreserba> erreserbaLortu() throws SQLException {
            ObservableList<Erreserba> lista = FXCollections.observableArrayList();

            String query = "SELECT izena, mahaiZenbakia, data, pertsonaKop, kantzelatuta, updateData, updateBy FROM dberronka.erreserba";
            try (Connection conn = DBconexion.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    lista.add(new Erreserba(
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
    public static void erreserbaGehitu(String izena, int mahaiZenbakia, Date data, int pertsonaKop){

        String query = "INSERT INTO erreserba (izena, mahaiZenbakia, data, pertsonaKop, kantzelatuta) VALUES ( ?, ?, ?, ?, 0)";
        Connection conn = null;
        try {

            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, izena);
            stmt.setInt(2, mahaiZenbakia);
            stmt.setDate(3, data);
            stmt.setInt(4, pertsonaKop);

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
