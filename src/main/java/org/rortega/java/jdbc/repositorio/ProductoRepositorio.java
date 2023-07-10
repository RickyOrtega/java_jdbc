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

            while (rs.next()) {
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

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    producto = crearProducto(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("No se pudo hacer la solicitud a la Base de Datos");
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos (nombre, precio, fecha_registro) values (?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(3, producto.getId());
            } else{
                stmt.setDate(3, (Date) producto.getFechaRegistro());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("No se pudo insertar el producto.");
        }
    }

    @Override
    public void eliminar(Long id) {

        String query = "DELETE FROM productos WHERE id = ?";

        try(PreparedStatement stmt = getConnection().prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar el producto.");
        }
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
