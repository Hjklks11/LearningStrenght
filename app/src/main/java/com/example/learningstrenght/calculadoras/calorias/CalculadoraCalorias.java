package com.example.learningstrenght.calculadoras.calorias;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CalculadoraCalorias {
    Map<String, Macros> mapMacros;
    Integer peso, altura, edad;
    String sexo;
    Double actividad;

    public CalculadoraCalorias(Integer peso, Integer altura, Integer edad, String sexo, Double actividad) {
        this.peso = peso;
        this.altura = altura;
        this.edad = edad;
        this.sexo = sexo;
        this.actividad = actividad;
    }

    public Map<String, Macros> getMacros(){
        mapMacros = new HashMap<>();

        Double calorias = calculoCalorias();

        mapMacros.put("Volumen", calculoMacros(calorias + 300, "Volumen"));
        mapMacros.put("Definicion", calculoMacros(calorias - 300, "Definicion"));
        mapMacros.put("Mantenimiento", calculoMacros(calorias, "Mantenimiento"));

        return mapMacros;
    }

    private Double calculoCalorias() {
        Double calorias;
        calorias = 10 * peso + 6.25 * altura - 5 * edad;
        if (sexo.equals("H"))
            calorias += 5;
        else
            calorias -= 161;
        return calorias * actividad;
    }

    private Macros calculoMacros(Double calorias, String tipo) {
        Double prote, grasas, carbos;
        if (tipo.equals("Volumen")){
            prote = peso * 2d; // gramos de proteina
            grasas = calorias * 0.3; // calorias de grasas
            carbos = calorias - prote * 4 - grasas; // calorias de carbos
        } else if(tipo.equals("Definicion")) {
            prote = peso * 3d;
            grasas = calorias * 0.3;
            carbos = calorias - prote * 4 - grasas;
        } else {
            prote = peso * 3d;
            grasas = calorias * 0.3;
            carbos = calorias - prote * 4 - grasas;
        }
        return new Macros(calorias, prote, grasas / 9, carbos / 4); // gramos de cada
    }

}
