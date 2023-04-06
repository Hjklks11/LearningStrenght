package Entities;

import android.arch.persistence.room.Entity;

@Entity(primaryKeys = {"idRutina","idEjercicio"})
public class EjerciciosPorRutinaCrossRef {
    public int idRutina;
    public int idEjercicio;
}

