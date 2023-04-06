package com.example.learningstrenght;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class CalculadorRMActivity extends AppCompatActivity {
    private Spinner spinnerEjercicios;
    private TextView TextViewEjercicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculador_rmactivity);
        this.spinnerEjercicios = (Spinner) findViewById(R.id.spinnerEjercicios);
        this.TextViewEjercicio = (TextView) findViewById(R.id.textViewEjercicioNombreCalculadora);
        List<String> options = Arrays.asList("Squat", "Bench", "DeadLift");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEjercicios.setAdapter(adapter);
        spinnerEjercicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}