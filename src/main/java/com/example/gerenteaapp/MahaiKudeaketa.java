package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

public class MahaiKudeaketa {

    public static ObservableList<Mahaia> mahaiaLortu() throws SQLException {
        ObservableList<Mahaia> lista = FXCollections.observableArrayList();

        String query = "SELECT * FROM mahaia";
        try (Connection conn = DBconexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                lista.add(new Mahaia(
                        rs.getInt ("id"),
                        rs.getInt("mahaiZenbakia"),
                        rs.getInt("kopurua"),
                        rs.getDate("updateDate"),
                        rs.getString("updateBy")

                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }

        return lista;
    }
    public static boolean mahaiaGehitu(int mahaiZenbakia, int kopurua) {

        String query = "INSERT INTO mahaia (mahaiZenbakia, kopurua) VALUES (?, ?)";
        Connection conn = null;
        try {

            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, mahaiZenbakia);
            stmt.setInt(2, kopurua);

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

        String query = "UPDATE mahaia SET kopurua = ?, updateDate = ?, updateBy = ? WHERE id = ?";
        Connection conn = null;
        try{
            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, kopurua);
            stmt.setInt(4, id);
            stmt.setDate(2, updateData);
            stmt.setString(3, updateBy);
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
