package com.example.gerenteaapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InicioSesion {
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
}
