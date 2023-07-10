package org.rortega.java.jdbc.repositorio;

import org.rortega.java.jdbc.modelo.Producto;
import org.rortega.java.jdbc.util.ConexionBaseDatos;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio implements Repositorio<Producto> {

   private Connection getConnection(){
       return ConexionBaseDatos.getInstance();
   }

    @Override
    public List<Producto> listar() {
       List<Producto> productos = new ArrayList<>();

       try(Statement stmt = getConnection().createStatement();
           ResultSet rs = stmt.executeQuery("SELECT * FROM productos")){

           while(rs.next()){
               Producto p = new Producto();
               p.setId(rs.getLong("id"));
               p.setNombre(rs.getString("nombre"));
               p.setPrecio(rs.getInt("precio"));
               p.setFechaRegistro(rs.getDate("fecha_creacion"));
               productos.add(p);
           }

       } catch (SQLException e) {
           System.out.println("No se pudieron obtener los productos de la base de datos");
       }
        return productos;
    }

    @Override
    public Producto porId(Long id) {
        return null;
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }
}
