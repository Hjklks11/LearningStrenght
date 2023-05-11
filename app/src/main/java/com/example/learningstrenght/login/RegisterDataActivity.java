package com.example.learningstrenght.login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.learningstrenght.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class RegisterDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // TODO: cambiar edad por fecha de nacimiento
    private TextInputEditText txtUsuario, txtFecha, txtPeso, txtAltura, txtRm1, txtRm2, txtRm3;
    private Spinner spinnerDeporte;
    private LinearLayout layoutRm;
    private Button btnEntrar;
    private String usuario, edad, peso, altura, deporte, rm1, rm2, rm3;
    private Map<String, String> mapUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);

        inicializarEditText();

        inicializarSpinner();

        listeners();
    }

    private void setRm(String rm1, String rm2, String rm3, String tipo) {
        txtRm1.setHint(tipo);
        txtRm2.setHint(tipo);
        txtRm3.setHint(tipo);

        txtRm1.setText(rm1);
        txtRm2.setText(rm2);
        txtRm3.setText(rm3);
    }

    private void listeners() {
        btnEntrar = findViewById(R.id.btnEntrarRegisterData);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recogerDatosTv();
                if (usuario.isEmpty() || edad.isEmpty()) {
                    Toast.makeText(RegisterDataActivity.this, "Por favor, rellene los campos marcados con un *.", Toast.LENGTH_SHORT).show();
                } else {
                    // TODO: Subir datos a la base de datos
                    subirABd();

                    enviarCorreoVerificacion();
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(RegisterDataActivity.this, LoginActivity.class));
                }
            }
        });

        txtFecha.setOnFocusChangeListener((view, b) -> {
            if (b) showDatePickerDialog();
        });
    }

    private void subirABd() {
        inicializarMapa();
    }

    private void enviarCorreoVerificacion() {
        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterDataActivity.this, "Te hemos enviado un correo de verificacion, verifica el correo antes de iniciar sesion.", Toast.LENGTH_LONG).show();
                } else {
                    Log.w(TAG, "Error al enviar el correo de confirmacion en RegisterDataActivity.");
                }
            }
        });
    }

    private void inicializarMapa() {
        mapUsuario = new HashMap<>();
        mapUsuario.put("Usuario", usuario);
        mapUsuario.put("FechaNac", edad);
        mapUsuario.put("Peso", peso);
        mapUsuario.put("Altura", altura);
        // TODO: si el deporte es culturismo los rm seran nulos
        if (!deporte.equals("Selecciona tu deporte")) {
            mapUsuario.put("Deporte", deporte);
            mapUsuario.put("Rm1", rm1);
            mapUsuario.put("Rm2", rm2);
            mapUsuario.put("Rm3", rm3);
        }
    }

    private void recogerDatosTv() {
        usuario = txtUsuario.getText().toString().trim();
        edad = txtFecha.getText().toString().trim();
        peso = txtPeso.getText().toString().trim();
        altura = txtAltura.getText().toString().trim();
        deporte = spinnerDeporte.getSelectedItem().toString();
        rm1 = txtRm1.getText().toString().trim();
        rm2 = txtRm2.getText().toString().trim();
        rm3 = txtRm3.getText().toString().trim();
    }

    private void inicializarEditText() {
        txtUsuario = findViewById(R.id.txtNombreUsuarioRegisterData);
        txtFecha = findViewById(R.id.txtFechaNacRegisterData);
        txtPeso = findViewById(R.id.txtPesoRegisterData);
        txtAltura = findViewById(R.id.txtAlturaRegisterData);
        txtRm1 = findViewById(R.id.txtRm1RegisterData);
        txtRm2 = findViewById(R.id.txtRm2RegisterData);
        txtRm3 = findViewById(R.id.txtRm3RegisterData);
    }

    private void inicializarSpinner() {
        layoutRm = findViewById(R.id.layoutRM);
        spinnerDeporte = findViewById(R.id.spinnerDeporteRegisterData);
        spinnerDeporte.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (spinnerDeporte.getSelectedItem().toString()) {
            case "Calistenia":
                setRm("Dominadas", "Fondos", "Flexiones", "repes");
                layoutRm.setVisibility(View.VISIBLE);
                break;
            case "Crossfit":
                Toast.makeText(RegisterDataActivity.this, "Para eso vete al zoo", Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finishAffinity();
                break;
            case "Culturismo":
                layoutRm.setVisibility(View.GONE);
                break;
            case "Powerlifting":
                setRm("Press banca", "Peso muerto", "Sentadilla", "kg");
                layoutRm.setVisibility(View.VISIBLE);
                break;
            case "Streetlifting":
                setRm("Dominadas", "Fondos", "Sentadilla", "kg");
                layoutRm.setVisibility(View.VISIBLE);
                break;
            default:
                layoutRm.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        layoutRm.setVisibility(View.GONE);
    }

    private void showDatePickerDialog() {
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Fecha nacimiento").build();

        materialDatePicker.addOnPositiveButtonClickListener(selection -> txtFecha.setText("" + materialDatePicker.getHeaderText()));

        materialDatePicker.addOnDismissListener(dialogInterface -> txtFecha.clearFocus());

        materialDatePicker.show(getSupportFragmentManager(), "TAG");
    }
}