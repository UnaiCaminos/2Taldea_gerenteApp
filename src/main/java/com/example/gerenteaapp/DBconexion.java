package com.example.gerenteaapp;

import java.sql.*;

public class DBconexion {

        // private static final String url = "jdbc:mysql://192.168.115.158:3306/2taldea";
        // private static final String user = "2Taldea";
        // private static final String password = "2Taldea";

        private static final String url = "jdbc:mysql://localhost:3306/dberronka";
        private static final String user = "root";
        private static final String password = "1WMG2023";

        public static Connection getConnection() throws SQLException, ClassNotFoundException {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(url, user, password);
        }

}
