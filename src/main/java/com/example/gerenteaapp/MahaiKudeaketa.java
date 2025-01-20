package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

public class MahaiKudeaketa {

    public static ObservableList<Mahaia> mahaiaLortu() throws SQLException {
        ObservableList<Mahaia> lista = FXCollections.observableArrayList();

        String query = "SELECT * FROM dberronka.mahaia";
        try (Connection conn = DBconexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lista.add(new Mahaia(
                        rs.getInt ("id"),
                        rs.getInt("mahaiZenbakia"),
                        rs.getInt("kopurua"),
                        rs.getDate("updateData"),
                        rs.getString("updateBy")

                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }

        return lista;
    }
    public static boolean mahaiaGehitu(String mahaiZenb, int kop) {

        String query = "INSERT INTO mahaia (mahaiZenbakia, kopurua) VALUES (?, ?)";
        Connection conn = null;
        try {

            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, mahaiZenb);
            stmt.setInt(2, kop);

            stmt.executeUpdate();
            int filasAfectadas = stmt.executeUpdate();

            boolean insertExitoso = filasAfectadas > 0;

            if (insertExitoso) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Mahaia> mahaiaBilatu() throws SQLException {
        ObservableList<Mahaia> lista = FXCollections.observableArrayList();

        String query = "SELECT * FROM dberronka.mahaia WHERE izena = ?";
        try (Connection conn = DBconexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lista.add(new Mahaia(
                        rs.getInt("id"),
                        rs.getInt("mahaiZenbakia"),
                        rs.getInt("kopurua"),
                        rs.getDate("updateData"),
                        rs.getString("updateBy")
                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }

        return lista;
    }
    public static boolean mahaiaKendu(int id) {

        String query = "DELETE FROM mahaia WHERE id = ?";
        Connection conn = null;
        try{
            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            boolean deleteExitoso = rowsAffected > 0;

            if (deleteExitoso) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean mahaiaAldatu(int id, int kopurua, String updateBy, Date updateData) {

        String query = "UPDATE mahaia SET kopurua = ?, updateData = ?, updateBy = ? WHERE id = ?";
        Connection conn = null;
        try{
            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, kopurua);
            stmt.setInt(2, id);
            stmt.setDate(3, updateData);
            stmt.setString(4, updateBy);
            int rowsAffected = stmt.executeUpdate();

            boolean updateExitoso = rowsAffected > 0;

            if (updateExitoso) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
