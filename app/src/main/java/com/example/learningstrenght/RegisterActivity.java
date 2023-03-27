package com.example.learningstrenght;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button btnLogin;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.editTextCorreoRegister);
        password = findViewById(R.id.editTextContrasenaRegister);
        btnLogin = findViewById(R.id.btnCrearCuentaRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (emailUser.isEmpty() || passUser.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Tienes que ingresar correo y contraseña.", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(emailUser, passUser);
                }
            }
        });
    }
    private void registerUser(String emailUser, String passUser) {
        mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    /*finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));*/
                    Toast.makeText(RegisterActivity.this, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show();
                } else{
                    //Toast.makeText(RegisterActivity.this, "error al crear cuenta", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}