package com.miguel_lm.appjardin.core;

import android.content.Context;
import androidx.room.Room;
import java.util.List;

public class RepositorioPlantas {

    private static RepositorioPlantas repositorioPlantas;

    private final DAOPlanta daoPlanta;

    public static RepositorioPlantas getInstance(Context context) {

        if (repositorioPlantas == null)
            repositorioPlantas = new RepositorioPlantas(context);

        return repositorioPlantas;
    }

    private RepositorioPlantas(Context context) {
        PlantaDataBase dataBase = Room.databaseBuilder(context.getApplicationContext(), PlantaDataBase.class, "plantas").allowMainThreadQueries().build();
        daoPlanta = dataBase.getDAOPlanta();
    }

    public List<Planta> obtenerPlantas() {
        return daoPlanta.obtenerPlantas();
    }

    public List<Planta> obtenerPlantasSeleccionadas() {
        return daoPlanta.obtenerPlantasSeleccionadas();
    }

    public void eliminarPlanta(Planta planta) {
        daoPlanta.eliminarPlanta(planta);
    }

    public void insertar(Planta nuevaPlanta) {
        if (daoPlanta.buscarPlanta(nuevaPlanta.getNombre()) == null) {
            daoPlanta.insertar(nuevaPlanta);
        }
    }

    public void borrarDatos() {
        daoPlanta.borrarDatos();
    }

    public void actualizarPlanta(Planta planta) {
        daoPlanta.actualizarPlanta(planta);
    }
}
