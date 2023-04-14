package com.example.learningstrenght.calculadoras.calorias;

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
        Integer prote;
        Double carbos;
        Double grasas;
        if (tipo.equals("Volumen")){
            prote = peso * 2;
            grasas = calorias * 0.3;
            carbos = calorias - prote - grasas;
        } else if(tipo.equals("Definicion")) {
            prote = peso * 3;
            grasas = calorias * 0.3;
            carbos = calorias - prote - grasas;
        } else {
            prote = peso * 3;
            grasas = calorias * 0.3;
            carbos = calorias - prote - grasas;
        }
        return new Macros(calorias, prote * 4, grasas * 9, carbos * 4);
    }

}
