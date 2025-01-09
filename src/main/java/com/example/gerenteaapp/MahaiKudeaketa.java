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
                        rs.getInt("id"),
                        rs.getInt("mahaiZenbakia"),
                        rs.getInt("kopurua")
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
}
