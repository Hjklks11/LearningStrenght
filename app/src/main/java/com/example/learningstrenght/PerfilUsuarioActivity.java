package com.example.learningstrenght;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilUsuarioActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputLayout tilFecha;
    private TextInputEditText txtFecha;

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

        tilFecha = findViewById(R.id.tilFechaNacPerfilusuario);
        txtFecha = findViewById(R.id.txtFechaNacPerfilusuario);

        txtFecha.setOnFocusChangeListener((view, b) -> {
            if (b) {
                showDatePickerDialog();
            } else {
                txtFecha.setFocusable(true);
            }
        });
    }

    //Este metodo va en el onClickListener del txtDate
    private void showDatePickerDialog() {
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Fecha nacimiento").build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                txtFecha.setText(""+materialDatePicker.getHeaderText());
            }
        });
        materialDatePicker.addOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                txtFecha.clearFocus();
            }
        });

        materialDatePicker.show(getSupportFragmentManager(), "TAG");
    }
}