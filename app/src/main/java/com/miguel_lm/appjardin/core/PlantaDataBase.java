package com.miguel_lm.appjardin.core;

import androidx.room.*;

@Database(entities = {Planta.class}, version = 1, exportSchema = false)
public abstract class PlantaDataBase extends RoomDatabase {
    public abstract DAOPlanta getDAOPlanta();
}
