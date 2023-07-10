package org.rortega.java.jdbc;

import org.rortega.java.jdbc.modelo.Producto;
import org.rortega.java.jdbc.repositorio.ProductoRepositorio;
import org.rortega.java.jdbc.repositorio.Repositorio;
import org.rortega.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;

public class EjemploJDBCSinFinally {
    public static void main(String[] args) {

        try (Connection con = ConexionBaseDatos.getInstance()){

            Repositorio<Producto> repositorio = new ProductoRepositorio();
            System.out.println("==============Listar===============");
            repositorio.listar().forEach(System.out::println);


            System.out.println("==============Obtener por ID===============");
            System.out.println(repositorio.porId(2L));


            System.out.println("==============Insertar Nuevo Producto===============");
            Producto p = new Producto();
            p.setNombre("Laptop ASUS TUF Gaming f15");
            p.setPrecio(800);
            p.setFechaRegistro(new Date());
            repositorio.crear(p);
            repositorio.listar().forEach(System.out::println);
        } catch (SQLException e) {
            System.err.println("No se pudo conectar a la Base de Datos.");
        }
    }
}