package com.example.learningstrenght.calculadoras.rm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learningstrenght.R;

public class FormulasActivity extends AppCompatActivity {
    LinearLayout layout;
    ImageView imagen;
    TextView txt;
    String formula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_rm_formulas);

        formula = getIntent().getStringExtra("Formula");

        Drawable foto = Drawable.createFromPath("@drawable/" + formula);

        layout = findViewById(R.id.layoutFormulas);
        imagen = findViewById(R.id.imagenFormulas);
        txt = findViewById(R.id.txtFormulas);

        imagen.setImageDrawable(foto);

        layout.setOnClickListener(view -> startActivity(new Intent(FormulasActivity.this, CalculadoraRmActivity.class)));
    }
}