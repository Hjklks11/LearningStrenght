package Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.annotation.NonNull;

@Entity(tableName = "Ejercicios")
public class Ejercicio {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="IdEjercicio")
    private int idEjercicio;
    @ColumnInfo(name = "nombreEjercicio")
    private String nombreEjercicio;
    @ColumnInfo(name="TipoEjercicio")
    private String tipoEjercicio;

    public Ejercicio(String nombreEjercicio, String tipoEjercicio) {

        this.nombreEjercicio = nombreEjercicio;
        this.tipoEjercicio = tipoEjercicio;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(String tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }
    @Override
    public String toString() {
        return "Ejercicio{" +
                "idEjercicio=" + idEjercicio +
                ", nombreEjercicio='" + nombreEjercicio + '\'' +
                ", TipoEjercicio='" + tipoEjercicio + '\'' +
                '}';
    }
}
