package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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

    public static boolean erreserbaKendu(int id) {

        String query = "DELETE FROM erreserba WHERE id = ?";
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
    public static boolean erreserbaAldatu(String izena, int mahaiZenb, Date data, int pertsonaKop, boolean kantzelatuta, String updateBy, int id) throws SQLException {

        String query = "UPDATE erreserba SET izena = ?, mahaiZenbakia = ?, data = ?, pertsonaKop = ?, kantzelatuta = ?, updateBy = ? WHERE id = ?";
        Connection conn = null;
        try{
            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, izena);
            stmt.setInt(2, mahaiZenb);
            stmt.setDate(3, data);
            stmt.setInt(4, pertsonaKop);
            stmt.setBoolean(5, kantzelatuta);
            stmt.setString(6, updateBy);
            stmt.setInt(7,id);
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
