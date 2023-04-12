package Entities;

import androidx.room.Embedded;
import androidx.room.Entity;

import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;
@Entity(tableName = "EjerciciosPorUsuario")
public class EjerciciosPorUsuario {

    @Embedded
    public Usuarios usuarios;
    @Relation(
            parentColumn = "idUsuario",
            entityColumn = "idEjercicio",
            associateBy = @Junction(EjerciciosPorUsuarioCrossRef.class)
    )
    public List<Ejercicio> ejercicios;
}
