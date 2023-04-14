package com.example.learningstrenght.calculadoras.calorias;

public class Macros {
    Integer prote;
    Double calorias, carbos, grasas;

    public Macros(Double calorias, Integer prote, Double carbos, Double grasas) {
        this.calorias = calorias;
        this.prote = prote;
        this.carbos = carbos;
        this.grasas = grasas;
    }

    @Override
    public String toString() {
        return "Calorias -> " + calorias + "kcal"
                + "\nProteinas: " + prote + "g"
                + "\nCarbohidratos: " + carbos + "g"
                + "\nGrasas: " + grasas + "g";
    }
}
