package com.miguel_lm.appjardin.core;

import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.Image;

import androidx.annotation.NonNull;
import androidx.room.*;

@Entity(tableName = "Plantas")
public class Planta {

    @PrimaryKey(autoGenerate = true)
    protected int key;
    @NonNull
    private Picture imagen;
    @NonNull
    private String nombre;

    public Planta(Picture imagen, String nombre){
       this.imagen = imagen;
       this.nombre = nombre;
    }

    public Picture getImagen() {
        return imagen;
    }

    public void setImagen(Picture imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
