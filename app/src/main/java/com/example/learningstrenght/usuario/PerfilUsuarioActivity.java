package com.example.learningstrenght.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learningstrenght.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class PerfilUsuarioActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputLayout tilUsuario, tilNombre, tilEmail, tilFecha, tilPeso, tilAltura;
    private TextInputEditText txtUsuario, txtNombre, txtEmail, txtFecha, txtPeso, txtAltura;
    private FloatingActionButton btnAtras, btnAceptar;
    private MaterialButton btnCambiarFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        user.getDisplayName();
        user.getEmail();
        user.getPhoneNumber();
        user.getPhotoUrl();
        user.getMetadata().getCreationTimestamp();

        inicializarComponentes();

        listeners();


    }

    private void inicializarComponentes() {
        tilUsuario = findViewById(R.id.tilNombreUsuarioPerfilUsuario);
        txtUsuario = findViewById(R.id.txtNombreUsuarioPerfilUsuario);
        tilNombre = findViewById(R.id.tilNombrePerfilUsuario);
        txtNombre = findViewById(R.id.txtNombrePerfilUsuario);
        tilEmail = findViewById(R.id.tilEmailPerfilUsuario);
        txtEmail = findViewById(R.id.txtEmailPerfilUsuario);
        tilFecha = findViewById(R.id.tilFechaNacPerfilUsuario);
        txtFecha = findViewById(R.id.txtFechaNacPerfilUsuario);
        tilPeso = findViewById(R.id.tilPesoPerfilUsuario);
        txtPeso = findViewById(R.id.txtPesoPerfilUsuario);
        tilAltura = findViewById(R.id.tilAlturaPerfilUsuario);
        txtAltura = findViewById(R.id.txtAlturaPerfilUsuario);

        btnAtras = findViewById(R.id.btnAtrasPerfilUsuario);
        btnAceptar = findViewById(R.id.btnAceptarPerfilUsuario);
        btnCambiarFoto = findViewById(R.id.btnCambiarFotoPerfilUsuario);
    }

    private void listeners() {

        btnAtras.setOnClickListener(view -> finish());

        btnAceptar.setOnClickListener(view -> modificarDatosUsuario());

        btnCambiarFoto.setOnClickListener(view -> cambiarfoto());

        txtFecha.setOnFocusChangeListener((view, b) -> {
            if (b) {
                showDatePickerDialog();
            } else {
                txtFecha.setFocusable(true);
            }
        });
    }

    private void modificarDatosUsuario() {
        if (chequearDatos()) {
            HashMap<String, String> mapUsuario = recogerDatosUsuario();

        } else {
            txtUsuario.setError(" ");
            txtNombre.setError(" ");
            txtEmail.setError(" ");
            txtFecha.setError(" ");
            Toast.makeText(this, "Los campos marcados en rojo son obligatorios.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean chequearDatos() {
        if (!txtUsuario.getText().toString().trim().isBlank() && !txtNombre.getText().toString().trim().isBlank()
                && !txtEmail.getText().toString().trim().isBlank() && !txtFecha.getText().toString().trim().isBlank()) {
            return true;
        }
        return false;
    }

    private HashMap<String, String> recogerDatosUsuario() {
        HashMap<String, String> mapDatosUsuario = new HashMap<>();

        mapDatosUsuario.put("Usuario", txtUsuario.getText().toString().trim());
        mapDatosUsuario.put("Nombre", txtNombre.getText().toString().trim());
        mapDatosUsuario.put("Email", txtEmail.getText().toString().trim());
        mapDatosUsuario.put("Fecha", txtFecha.getText().toString().trim());
        if (!txtPeso.getText().toString().isBlank())        mapDatosUsuario.put("Peso", txtPeso.getText().toString().trim());
        if (!txtAltura.getText().toString().trim().isBlank())        mapDatosUsuario.put("Altura", txtAltura.getText().toString().trim());

        return mapDatosUsuario;
    }

    private void cambiarfoto() {
        //TODO: cambiar foto
    }

    private void showDatePickerDialog() {
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Fecha nacimiento").build();

        materialDatePicker.addOnPositiveButtonClickListener(selection -> txtFecha.setText("" + materialDatePicker.getHeaderText()));

        materialDatePicker.addOnDismissListener(dialogInterface -> txtFecha.clearFocus());

        materialDatePicker.show(getSupportFragmentManager(), "TAG");
    }
}