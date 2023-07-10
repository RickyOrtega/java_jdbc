package org.rortega.java.jdbc;

import org.rortega.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;

public class EjemploJDBCSinFinally {
    public static void main(String[] args) {

        try (Connection con = ConexionBaseDatos.getInstance()){
            Statement stmt = con.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM productos");

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
        }
    }
}