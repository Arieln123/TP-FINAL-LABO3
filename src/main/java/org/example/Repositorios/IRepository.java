package org.example.Repositorios;


import java.util.ArrayList;

public interface IRepository <T>{
    void cargar();
    void guardar();
    ArrayList<T> listar();
    void agregar(T...objeto);
    void eliminar(T objeto);
    void modificar(T objeto);
    boolean existe(T objeto);
    T info(T objeto);


}