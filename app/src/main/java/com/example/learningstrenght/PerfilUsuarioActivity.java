package com.example.learningstrenght;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PerfilUsuarioActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

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
    }

    //Este metodo va en el onClickListener del txtDate
    private void showDatePickerDialog() {
        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Fecha nacimiento").build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                //txtDate.setText(""+materialDatePicker.getHeaderText());
            }
        });

        materialDatePicker.show(getSupportFragmentManager(), "TAG");
    }
}