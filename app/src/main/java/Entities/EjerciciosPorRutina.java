package Entities;


import androidx.room.Embedded;
import androidx.room.Entity;


import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;
@Entity(tableName = "EjerciciosPorRutina")
public class EjerciciosPorRutina {
    @Embedded public Rutina rutina;
    @Relation(
            parentColumn = "idRutina",
            entityColumn = "idEjercicio",
            associateBy = @Junction(EjerciciosPorRutinaCrossRef.class)
    )
    public List<Ejercicio> ejercicios;
}
