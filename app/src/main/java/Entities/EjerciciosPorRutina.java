package Entities;


import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;


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
