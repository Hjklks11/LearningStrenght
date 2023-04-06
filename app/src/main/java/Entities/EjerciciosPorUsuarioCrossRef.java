package Entities;

import android.arch.persistence.room.Entity;

public class EjerciciosPorUsuarioCrossRef {
    @Entity(primaryKeys = {"idUsuario","idEjercicio"})
    public class EjerciciosPorRutinaCrossRef {
        public int idUsuario;
        public int idEjercicio;
    }
}
