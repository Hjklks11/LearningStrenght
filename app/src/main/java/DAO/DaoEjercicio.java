package DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Entities.Ejercicio;

@Dao
public interface DaoEjercicio {

    @Insert
    abstract void insert(Ejercicio ejercicio);
    @Delete
    void BorrarContacto(Ejercicio ejercicio);
    @Query("SELECT * from Ejercicios ORDER BY nombreEjercicio desc")
    LiveData<List<Ejercicio>> seleccionarTablaEjercicios();

}
