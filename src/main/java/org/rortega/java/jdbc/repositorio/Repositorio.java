package org.rortega.java.jdbc.repositorio;

import java.util.List;

public interface Repositorio<T> {
    List<T> listar();
    T porId(Long id);
    void actualizar(T t);
    void crear(T t);
    void eliminar(Long id);
}
