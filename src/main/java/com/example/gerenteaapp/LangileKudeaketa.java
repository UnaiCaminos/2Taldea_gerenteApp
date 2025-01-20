package com.example.gerenteaapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
                        rs.getDate("updateData"),
                        rs.getString("updateBy")

                ));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error de conexión a la base de datos", e);
        }

        return lista;
    }
    public static void langileaGehitu(String dni, String izena, String abizena, String pasahitza, String korreoa, String telefonoa, String postua) {

        String query = "INSERT INTO langilea (dni, izena, abizena, pasahitza, korreoa, telefonoa, postua, txatBaimena) VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
        Connection conn = null;
        try {

            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, dni);
            stmt.setString(2, izena);
            stmt.setString(3, abizena);
            stmt.setString(4, pasahitza);
            stmt.setString(5, korreoa);
            stmt.setString(6, telefonoa);
            stmt.setString(7, postua);

            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean balioztatu(String erabiltzailea, String pasahitza) {
        boolean konexioa=false;
        String query = "SELECT COUNT(*) FROM dberronka.langilea WHERE izena = ? AND pasahitza = ? AND postua = 'Gerentea'";
        Connection conn = null;

        try {

            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, erabiltzailea);
            stmt.setString(2, pasahitza);


            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    //return rs.getInt(1) > 0;
                    konexioa = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Arazoa emen dago");
        }
        return konexioa;
    }
    public static boolean langileaKendu(int id) {

        String query = "DELETE FROM langilea WHERE id = ?";
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
    public static boolean langileaAldatu(String dni, String izena, String abizena, String pasahitza, String korreoa, String telefonoa, String postua, Date updateData, String updateBy, int id) throws SQLException {

        String query = "UPDATE erreserba SET dni = ?, izena = ?, abizena = ?, pasahitza = ?, korreoa = ?, telefonoa = ?, postua = ?, updateData = ?, updateBy = ?  WHERE id = ?";
        Connection conn = null;
        try{
            conn = DBconexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, dni);
            stmt.setString(2, izena);
            stmt.setString(3, abizena);
            stmt.setString(4, pasahitza);
            stmt.setString(5, korreoa);
            stmt.setString(6, telefonoa);
            stmt.setString(7,postua);
            stmt.setDate(8,updateData);
            stmt.setString(9,updateBy);
            stmt.setInt(10,id);

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
