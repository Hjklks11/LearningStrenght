package DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import Entities.EjerciciosPorRutina;

@Dao
public interface EjerciciosPorUsuario {

    @Transaction
    @Query("SELECT * FROM Usuarios")
    public List<EjerciciosPorUsuario> getEjerciciosPorUsuario();

}
