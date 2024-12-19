package com.example.gerenteaapp;

import java.sql.*;

public class DBconexion {

        private static final String url = "jdbc:mysql://localhost:3306/dbErronka";
        private static final String user = "root";
        private static final String password = "1WMG2023";

        public static Connection getConnection() throws SQLException, ClassNotFoundException {
                Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        }

}
