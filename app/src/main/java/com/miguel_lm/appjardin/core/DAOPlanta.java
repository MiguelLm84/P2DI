package com.miguel_lm.appjardin.core;

import androidx.room.*;

import java.util.List;

@Dao
public interface DAOPlanta {

    @Delete
    void eliminarPlanta(Planta planta);

    @Insert
    void insertar(Planta planta);

    @Query("SELECT * FROM Plantas")
    List<Planta> obtenerPlantas();

    @Update
    void actualizarPlanta(Planta planta);

}
