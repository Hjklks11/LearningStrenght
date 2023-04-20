package com.example.learningstrenght.calculadoras.calorias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learningstrenght.R;

import java.util.Map;

public class CalculadoraCaloriasActivity extends AppCompatActivity {

    EditText peso, altura, edad;
    TextView volumen, definicion, mantenimiento, macrosVolumen, macrosDefinicion, macrosMantenimiento;
    RadioGroup radioGroup;
    RadioButton rbHombre, rbMujer;
    Spinner spinnerActividad, spinnerObjetivo;
    Button btnInfo, btnCalcular;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_calorias);

        inicializarComponentes();

        listeners();

    }

    private void listeners() {
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalculadoraCaloriasActivity.this, InfoActivity.class));
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);

                String sPeso = peso.getText().toString().trim();
                String sAltura = altura.getText().toString().trim();
                String sEdad = edad.getText().toString().trim();
                String sexo = "";
                if (rbHombre.isChecked()) sexo = "H"; else if (rbMujer.isChecked()) sexo = "M";
                String sActividad = spinnerActividad.getSelectedItem().toString();
                String objetivo = spinnerObjetivo.getSelectedItem().toString();

                if (!sPeso.isEmpty() && !sAltura.isEmpty() && !sEdad.isEmpty() && !sexo.isEmpty() && spinnerActividad.getSelectedItemPosition() >= 1) {
                    CalculadoraCalorias calCal = new CalculadoraCalorias(Integer.parseInt(sPeso), Integer.parseInt(sAltura), Integer.parseInt(sEdad)
                    , sexo, Double.parseDouble(sActividad.split("->")[0]), objetivo);
                    Map<String, Macros> mapMacros = calCal.getMacros();

                    mostrarMacros(mapMacros);
                }

            }
        });
    }

    private void mostrarMacros(Map<String, Macros> mapMacros) {
        String objetivo = spinnerObjetivo.getSelectedItem().toString();
        if (objetivo.contains("Definicion")){
            definicion.setVisibility(View.VISIBLE);
            macrosDefinicion.setText(mapMacros.get("Definicion").toString());

        } else if (objetivo.contains("Volumen")) {
            volumen.setVisibility(View.VISIBLE);
            macrosVolumen.setText(mapMacros.get("Volumen").toString());

        } else if (objetivo.equals("Mantenimiento")) {
            mantenimiento.setVisibility(View.VISIBLE);
            macrosMantenimiento.setText(mapMacros.get("Mantenimiento").toString());

        } else {
            volumen.setVisibility(View.VISIBLE);
            definicion.setVisibility(View.VISIBLE);
            mantenimiento.setVisibility(View.VISIBLE);
            macrosVolumen.setText(mapMacros.get("Volumen").toString());
            macrosDefinicion.setText(mapMacros.get("Definicion").toString());
            macrosMantenimiento.setText(mapMacros.get("Mantenimiento").toString());
        }
    }

    private void inicializarComponentes() {
        btnInfo = findViewById(R.id.btnInfoCalculadoraCalorias);
        peso = findViewById(R.id.txtPesoCalculadoraMacros);
        altura = findViewById(R.id.txtAlturaCalculadoraMacros);
        edad = findViewById(R.id.txtEdadCalculadoraMacros);
        radioGroup = findViewById(R.id.radioGroupCalculadoraMacros);
        rbHombre = findViewById(R.id.rbtnHombreCalculadoraMacros);
        rbMujer = findViewById(R.id.rbtnMujerCalculadoraMacros);
        spinnerActividad = findViewById(R.id.spinnerActividadCalculadoraMacros);
        spinnerObjetivo = findViewById(R.id.spinnerObjetivoCalculadoraCalorias);
        btnCalcular = findViewById(R.id.btnCalcularCalculadoraMacros);

        volumen = findViewById(R.id.txtVolumenCalculadoraCalorias);
        definicion = findViewById(R.id.txtDefinicionCalculadoraCalorias);
        mantenimiento = findViewById(R.id.txtMantenimientoCalculadoraCalorias);
        macrosVolumen = findViewById(R.id.txtMacrosVolumenCalculadoraCalorias);
        macrosDefinicion = findViewById(R.id.txtMacrosDefinicionCalculadoraCalorias);
        macrosMantenimiento = findViewById(R.id.txtMacrosMantenimientoCalculadoraCalorias);
    }
    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
