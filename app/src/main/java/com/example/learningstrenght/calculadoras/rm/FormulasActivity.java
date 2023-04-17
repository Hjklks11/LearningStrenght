package com.example.learningstrenght.calculadoras.rm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
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
        setContentView(R.layout.activity_formulas);

        formula = getIntent().getStringExtra("Formula");

        Drawable foto = Drawable.createFromPath("@drawable/" + formula);

        layout = findViewById(R.id.layoutFormulas);
        imagen = findViewById(R.id.imagenFormulas);
        txt = findViewById(R.id.txtFormulas);

        //txt.setWidth(foto.getIntrinsicWidth());
        imagen.setImageDrawable(foto);

        layout.setOnClickListener(view -> startActivity(new Intent(FormulasActivity.this, CalculadoraRmActivity.class)));
        //imagen.setOnClickListener(view -> startActivity(new Intent(FormulasActivity.this, CalculadoraRmActivity.class)));
        //txt.setOnClickListener(view -> startActivity(new Intent(FormulasActivity.this, CalculadoraRmActivity.class)));
    }
}