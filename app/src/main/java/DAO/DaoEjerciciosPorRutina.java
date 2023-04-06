package DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import Entities.EjerciciosPorRutina;

@Dao
public interface DaoEjerciciosPorRutina {

    @Transaction
    @Query("SELECT * FROM Rutina")
    public List<EjerciciosPorRutina> getEjerciciosPorRutina();

}

