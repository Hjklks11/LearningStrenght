package com.example.learningstrenght;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class RegisterDataActivity extends AppCompatActivity {
    private TextView tVUsuario, tVEdad, tVPeso, tVAltura, tVRmSentadilla, tVRmBanca, tVRmMuerto;
    private Button btnEntrar;
    private String usuario, edad, peso, altura, sentadilla, banca, muerto;
    private Map<String, String> mapUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);

        inicializarTextViews();

        recogerDatosTv();

        inicializarMapa();

        botonEntrar();
    }

    private void botonEntrar() {
        btnEntrar = findViewById(R.id.btnEntrarRegisterData);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usuario.isEmpty() || edad.isEmpty()) {
                    Toast.makeText(RegisterDataActivity.this, "Por favor, rellene los campos marcados con un *.", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Subir archivos a la base de datos
                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(RegisterDataActivity.this, LoginActivity.class));
                }
            }
        });
    }

    private void inicializarMapa() {
        mapUsuario = new HashMap<>();
        mapUsuario.put("Usuario", usuario);
        mapUsuario.put("Edad", edad);
        mapUsuario.put("Peso", peso);
        mapUsuario.put("Altura", altura);
        mapUsuario.put("Sentadilla", sentadilla);
        mapUsuario.put("Banca", banca);
        mapUsuario.put("Muerto", muerto);
    }

    private void recogerDatosTv() {
        usuario = tVUsuario.getText().toString().trim();
        edad = tVEdad.getText().toString().trim();
        peso = tVPeso.getText().toString().trim();
        altura = tVAltura.getText().toString().trim();
        sentadilla = tVRmSentadilla.getText().toString().trim();
        banca = tVRmBanca.getText().toString().trim();
        muerto = tVRmMuerto.getText().toString().trim();
    }

    private void inicializarTextViews() {
        tVUsuario = findViewById(R.id.txtNombreUsuarioRegisterData);
        tVEdad = findViewById(R.id.txtEdadRegisterData);
        tVPeso = findViewById(R.id.txtPesoRegisterData);
        tVAltura = findViewById(R.id.txtAlturaRegisterData);
        tVRmSentadilla = findViewById(R.id.txtRmSentadillaRegisterData);
        tVRmBanca = findViewById(R.id.txtRmBancaRegisterData);
        tVRmMuerto = findViewById(R.id.txtRmMuertoRegisterData);
    }
}