package DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Entities.Ejercicio;
import Entities.Rutina;

@Dao
public interface DaoRutina {
    @Insert
    abstract void insert(Rutina rutina);
    @Delete
    void BorrarRutina(Rutina rutina);
    @Query("SELECT * from Rutina ORDER BY NombreRutina desc")
    LiveData<List<Rutina>> seleccionarTablaRutinas();
}
