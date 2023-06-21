package com.example.learningstrenght.calculadoras.macros;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningstrenght.R;

public class InfoActivity extends AppCompatActivity {
    private LinearLayout layoutInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_macros_info);

        layoutInfo = findViewById(R.id.layoutInfoCalculadoraCalorias);
        layoutInfo.setOnClickListener(view -> {finish();overridePendingTransition(0,0);
        });
    }
}