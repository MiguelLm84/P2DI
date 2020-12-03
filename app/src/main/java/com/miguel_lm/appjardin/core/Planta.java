package com.miguel_lm.appjardin.core;

import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.io.Serializable;
import java.sql.Blob;

@Entity(tableName = "Plantas")
public class Planta  {

    @PrimaryKey(autoGenerate = true)
    protected int key;
    @NonNull
    private int imagen;
    @NonNull
    private String nombre;
    @NonNull
    private String datos;
    @NonNull
    private String descripcion;

    public Planta(int imagen, String nombre, String datos, String descripcion){
       this.imagen = imagen;
       this.nombre = nombre;
       this.datos=datos;
       this.descripcion=descripcion;
    }

    /** Constructor de copia */
    public Planta(Planta planta) {
        this.imagen = planta.imagen;
        this.nombre = planta.nombre;
        this.datos = planta.datos;
        this.descripcion = planta.descripcion;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.nombre = datos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.nombre = descripcion;
    }
}
