package org.rortega.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static final String path = "jdbc:mysql://localhost:3306/java_curso?serverTime=America/Bogota";
    private static final String user = "root";
    private static final String pass = "My_SQL$1998";
    private static Connection connection =null;

    public static Connection getInstance(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(path, user, pass);
            } catch (SQLException e) {
                System.out.println("No se pudo conectar a la Base de Datos");
            }
        }

        return connection;
    }
}