package com.example.learningstrenght;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.learningstrenght.calculadoras.calorias.CalculadoraCaloriasActivity;
import com.example.learningstrenght.calculadoras.rm.CalculadoraRmActivity;
import com.example.learningstrenght.login.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PantallaPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Button signOut, calculadoraRm, calculadoraMacros;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        mAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbarPantallaPrincipal);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.layoutPantallaPrincipal);
        navigationView = findViewById(R.id.navViewPantallaPrincipal);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.openNavDrawer, R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        findViewById();
        listenersBotones();
    }

    private void findViewById() {
        signOut = findViewById(R.id.btnSignOutPantallaPrincipal);
        calculadoraRm = findViewById(R.id.btnCalculadoraRmPantallaPrincipal);
        calculadoraMacros = findViewById(R.id.btnCalculadoraMacrosPantallaPrincipal);
    }

    private void listenersBotones() {
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(PantallaPrincipal.this, MainActivity.class));
            }
        });
        calculadoraRm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PantallaPrincipal.this, CalculadoraRmActivity.class));
            }
        });
        calculadoraMacros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PantallaPrincipal.this, CalculadoraCaloriasActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            startActivity(new Intent(PantallaPrincipal.this, MainActivity.class));
            signInAnonymous();
        }
    }
    private void signInAnonymous() {
        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(PantallaPrincipal.this, "Signed in Anonymously", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PantallaPrincipal.this, "Not Succesful. " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PantallaPrincipal.this, "Error entrando anonimamente." + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.w(TAG, "MainActivity. " + e.getMessage());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}