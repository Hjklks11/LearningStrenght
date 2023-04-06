package DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import Entities.Rutina;
import Entities.Usuarios;

@Dao
public interface DaoUsuarios {

    @Insert
    abstract void insert(Usuarios usuarios);
    @Delete
    void BorrarUsuario(Usuarios usuarios);
    @Query("SELECT * from Usuarios ORDER BY nombreUsuario desc")
    LiveData<List<Usuarios>> seleccionarTablaUsuarios();


}
