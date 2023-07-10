package org.rortega.java.jdbc;

import java.sql.*;

public class EjemploJDBC {
    public static void main(String[] args) {
        String path = "jdbc:mysql://localhost:3306/java_curso?serverTime=America/Bogota";
        String user = "root";
        String pass = "My_SQL$1998";

        Connection con = null;
        Statement stmt = null;
        ResultSet resultado = null;

        try {
            con = DriverManager.getConnection(path, user, pass);

            stmt = con.createStatement();
            resultado = stmt.executeQuery("SELECT * FROM productos");

            while(resultado.next()){

                System.out.println("--------------------------------"
                        .concat(String.valueOf(resultado.getInt(1)))
                        .concat("--------------------------------"));
                System.out.println("Nombre: ".concat(resultado.getString(2)));
                System.out.println("Precio: $".concat(resultado.getString(3)));
                System.out.println("Fecha creacion: ".concat(String.valueOf(resultado.getDate(4))));
            }
        } catch (SQLException e) {
            System.err.println("No se pudo conectar a la Base de Datos.");
        } finally {
            try {
                resultado.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("No se pudo cerrar la conexión a la Base de Datos.");
            }
        }
    }
}