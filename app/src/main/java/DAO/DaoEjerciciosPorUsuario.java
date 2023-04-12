package DAO;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface DaoEjerciciosPorUsuario {

    @Transaction
    @Query("SELECT * FROM Usuarios")
   public List<DaoEjerciciosPorUsuario> getEjerciciosPorUsuario();

}
