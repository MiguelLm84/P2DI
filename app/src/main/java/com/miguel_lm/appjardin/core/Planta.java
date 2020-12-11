package com.miguel_lm.appjardin.core;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Plantas")
public class Planta implements Serializable {

    @PrimaryKey(autoGenerate = true)
    protected int key;
    @NonNull
    private String imagen;
    @NonNull
    private int seleccionada;
    @NonNull
    private String nombre;
    @NonNull
    private String nombreCientifico;
    @NonNull
    private String temporada;
    @NonNull
    private String descripcion;

    public Planta(String imagen, @NonNull String nombre, @NonNull String nombreCientifico, @NonNull String temporada, @NonNull String descripcion, int seleccionada) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.temporada = temporada;
        this.descripcion = descripcion;
        this.seleccionada = seleccionada;
    }



    public Planta(Planta planta) {
        this.imagen = planta.imagen;
        this.nombre = planta.nombre;
        this.nombreCientifico = planta.nombreCientifico;
        this.temporada = planta.temporada;
        this.descripcion = planta.descripcion;
        this.seleccionada = planta.seleccionada;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.nombre = descripcion;
    }

    @NonNull
    public String getNombreCientifico() {
        return nombreCientifico;
    }

    @NonNull
    public String getTemporada() {
        return temporada;
    }

    public int getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(int seleccionada) {
        this.seleccionada = seleccionada;
    }

    public void setNombreCientifico(@NonNull String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public void setTemporada(@NonNull String temporada) {
        this.temporada = temporada;
    }


}
