package com.example.learningstrenght.calculadoras.rm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learningstrenght.R;

import java.util.Map;

public class CalculadoraRmActivity extends AppCompatActivity {
    private EditText peso, repes;
    private Button calcular;
    private TextView brzycki, epley;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_rm);
        inicializarComponentes();

        listeners();
    }

    private void listeners() {
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speso = peso.getText().toString().trim();
                String srepes = repes.getText().toString().trim();

                if (!speso.isEmpty() && !srepes.isEmpty()) {
                    mostrarRms(speso, srepes);
                }
            }
        });
        brzycki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculadoraRmActivity.this, FormulasActivity.class);
                intent.putExtra("Formula", "brzycki");
                startActivity(intent);
            }
        });
        epley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculadoraRmActivity.this, FormulasActivity.class);
                intent.putExtra("Formula", "epley");
                startActivity(intent);
            }
        });
    }

    private void mostrarRms(String speso, String srepes) {
        CalculadoraRm rm = new CalculadoraRm(Integer.parseInt(speso), Integer.parseInt(srepes));
        Map<String, Integer> mapRm = rm.getRm();
        //Map<String, Integer> mapRm = new CalculadoraRm(Integer.parseInt(speso), Integer.parseInt(srepes)).getRm();
        brzycki.setText("Brzycki    -->     Tu Rm son " + mapRm.get("Brzycki") + " kgs.");
        epley.setText("Epley    -->     Tu Rm son " + mapRm.get("Epley") + " kgs.");
    }

    private void inicializarComponentes() {
        peso = findViewById(R.id.txtPesoCalculadoraRm);
        repes = findViewById(R.id.txtRpsCalculadoraRm);
        calcular = findViewById(R.id.btnCalcularCalculadoraRm);
        brzycki = findViewById(R.id.txtBrzyckiCalculadoraRm);
        epley = findViewById(R.id.txtEpleyCalculadoraRm);
    }
}
