package Entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"idUsuario", "idEjercicio"})
public class EjerciciosPorUsuarioCrossRef {
    public int idUsuario;
    public int idEjercicio;
}
