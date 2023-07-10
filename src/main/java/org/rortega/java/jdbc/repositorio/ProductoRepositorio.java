package org.rortega.java.jdbc.repositorio;

import org.rortega.java.jdbc.modelo.Producto;
import org.rortega.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio implements Repositorio<Producto> {

    private Connection getConnection() {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {

            while(rs.next()){
                Producto p = crearProducto(rs);
                productos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("No se pudieron obtener los productos de la base de datos");
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) {
        Producto producto = null;
        try (PreparedStatement ps = getConnection()
                .prepareStatement("SELECT * FROM productos WHERE id = ?")) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                producto = crearProducto(rs);
            }

        } catch (SQLException e) {
            System.out.println("No se pudo hacer la solicitud a la Base de Datos");
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    private Producto crearProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setFechaRegistro(rs.getDate("fecha_creacion"));
        return p;
    }
}
