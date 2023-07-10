package org.rortega.java.jdbc;

import org.rortega.java.jdbc.modelo.Producto;
import org.rortega.java.jdbc.repositorio.ProductoRepositorio;
import org.rortega.java.jdbc.repositorio.Repositorio;
import org.rortega.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;

public class EjemploJDBCSinFinally {
    public static void main(String[] args) {

        try (Connection con = ConexionBaseDatos.getInstance()){

            Repositorio<Producto> repositorio = new ProductoRepositorio();

            repositorio.listar().forEach(p -> System.out.println(
                    "---------------".concat(String.valueOf(p.getId())).concat("---------------").concat("\n")
                            .concat(p.getNombre()).concat("\n")
                            .concat(String.valueOf(p.getPrecio())).concat("\n")
                            .concat(String.valueOf(p.getFechaRegistro())).concat("\n")
            ));

        } catch (SQLException e) {
            System.err.println("No se pudo conectar a la Base de Datos.");
        }
    }
}