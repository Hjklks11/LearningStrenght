package Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;

@Entity(tableName = "Rutina")
public class Rutina {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="IdRutina")
    private int idRutina;
    @ColumnInfo(name="NombreRutina")
    private String nombreRutina;
    @ColumnInfo(name="TipoRutina")
    private String tipoRutina;

    public Rutina(int idRutina, String nombreRutina, String tipoRutina) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
        this.tipoRutina = tipoRutina;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public String getTipoRutina() {
        return tipoRutina;
    }

    public void setTipoRutina(String tipoRutina) {
        this.tipoRutina = tipoRutina;
    }

    @Override
    public String toString() {
        return "Rutina{" +
                "idRutina=" + idRutina +
                ", nombreRutina='" + nombreRutina + '\'' +
                ", tipoRutina='" + tipoRutina + '\'' +
                '}';
    }

}
