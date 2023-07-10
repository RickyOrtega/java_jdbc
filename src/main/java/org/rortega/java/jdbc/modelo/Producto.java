package org.rortega.java.jdbc.modelo;

import java.util.Date;

public class Producto {
    private Long id;
    private String nomnre;
    private Integer precio;
    private Date fechaRegistro;

    public Producto(Long id, String nomnre, Integer precio, Date fechaRegistro) {
        this.id = id;
        this.nomnre = nomnre;
        this.precio = precio;
        this.fechaRegistro = fechaRegistro;
    }

    public Producto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomnre() {
        return nomnre;
    }

    public void setNomnre(String nomnre) {
        this.nomnre = nomnre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}