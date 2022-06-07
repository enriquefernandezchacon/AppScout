package com.scout.appscout.common;

public class Articulo {
    private String nombre;
    private int cantidad;
    private int imagen;

    // Constructor por defecto
    public Articulo() {
    }

    public Articulo(String nombre, int cantidad, int imagen) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    // Métodos de acceso

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
