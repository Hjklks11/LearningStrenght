package com.example.learningstrenght.Entidades;

public class Ejercicio {
    private String idEjercicio;
    private String grupoMuscular;
    private String nombreEjercicio;
    private String tipoEjercicio;
    private String tipoEntrenamiento;

    public Ejercicio(String grupoMuscular, String nombreEjercicio, String tipoEjercicio, String tipoEntrenamiento) {
        this.grupoMuscular = grupoMuscular;
        this.nombreEjercicio = nombreEjercicio;
        this.tipoEjercicio = tipoEjercicio;
        this.tipoEntrenamiento = tipoEntrenamiento;

    }

    public String getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(String idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
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

    public String getTipoEntrenamiento() {
        return tipoEntrenamiento;
    }

    public void setTipoEntrenamiento(String tipoEntrenamiento) {
        this.tipoEntrenamiento = tipoEntrenamiento;
    }

    @Override
    public String toString() {
        return "Ejercicio{" +
                "idEjercicio='" + idEjercicio + '\'' +
                ", grupoMuscular='" + grupoMuscular + '\'' +
                ", nombreEjercicio='" + nombreEjercicio + '\'' +
                ", tipoEjercicio='" + tipoEjercicio + '\'' +
                ", tipoEntrenamiento='" + tipoEntrenamiento + '\'' +
                '}';
    }
}
