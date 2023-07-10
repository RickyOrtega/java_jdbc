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

            repositorio.listar().forEach(System.out::println);

            System.out.println(repositorio.porId(2L));

        } catch (SQLException e) {
            System.err.println("No se pudo conectar a la Base de Datos.");
        }
    }
}