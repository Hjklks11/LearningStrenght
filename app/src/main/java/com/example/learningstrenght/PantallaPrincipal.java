package com.example.learningstrenght;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.learningstrenght.ajustes.SettingsFragment;
import com.example.learningstrenght.baseDeDatos.Firestore;
import com.example.learningstrenght.calculadoras.macros.CalculadoraMacrosFragment;
import com.example.learningstrenght.calculadoras.rm.CalculadoraRmFragment;
import com.example.learningstrenght.databinding.ActivityPantallaPrincipalBinding;
import com.example.learningstrenght.entidades.Usuario;
import com.example.learningstrenght.login.MainActivity;
import com.example.learningstrenght.rutinas.RutinasFragment;
import com.example.learningstrenght.usuario.PerfilUsuarioFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PantallaPrincipal extends AppCompatActivity {
    private FrameLayout frameLayoutPantallaPrincipal;
    private static FrameLayout layoutSecundario;
    public static ViewPager viewPager;
    private FirebaseAuth mAuth;
    private ActivityPantallaPrincipalBinding binding;
    private Firestore firestore;
    public static Boolean pantallaAncha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPantallaPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Inicializamos la vista acorde al ancho de pantalla
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float dp = metrics.widthPixels / (metrics.xdpi / 160);
        if (dp >= 600) {
            System.out.println("Pantalla tablet");
            pantallaAncha = true;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            System.out.println("Pantalla movil");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            pantallaAncha = false;
        }
        mAuth = FirebaseAuth.getInstance();
        firestore = Firestore.getInstance();
        if (mAuth.getCurrentUser() != null) recogerUsuario(mAuth.getCurrentUser().getUid());
        SharedPreferences sharedPreferences = getSharedPreferences(SettingsFragment.mPrefsName, MODE_PRIVATE);
        //ViewPager2
        PagerAdaptador pagerAdapter = new PagerAdaptador(getSupportFragmentManager());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        if (sharedPreferences.getString("listaCalculadoras", "Macros").equals("Rm")) viewPager.setCurrentItem(1);
        //Inicializar vista
        frameLayoutPantallaPrincipal = findViewById(R.id.frameLayoutPantallaPrincipal);
        layoutSecundario = findViewById(R.id.frameLayoutSencundarioPantallaPrincipal);
        replaceFragment(new RutinasFragment());
        //Botones de navegación
        binding.bottomNavigationViewPantallaPrincipal.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.Rutinas:
                    replaceFragment(new RutinasFragment());
                    break;
                case R.id.Calculadoras:
                    if(frameLayoutPantallaPrincipal.getVisibility() == View.VISIBLE) frameLayoutPantallaPrincipal.setVisibility(View.GONE);
                    if (pantallaAncha && layoutSecundario.getVisibility() == View.VISIBLE) layoutSecundario.setVisibility(View.GONE);
                    if (viewPager.getVisibility() == View.GONE) viewPager.setVisibility(View.VISIBLE);
                    break;
                case R.id.Perfil:
                    if (mAuth.getCurrentUser() == null || mAuth.getCurrentUser().isAnonymous()) {
                        startActivity(new Intent(PantallaPrincipal.this, MainActivity.class));
                    } else {
                        if (pantallaAncha && layoutSecundario.getVisibility() == View.VISIBLE) layoutSecundario.setVisibility(View.GONE);
                        replaceFragment(new PerfilUsuarioFragment());
                    }
                    break;
            }
            return true;
        });
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finishAffinity();
        }
        if (getIntent().getBooleanExtra("EDITAR", false)) {
            binding.bottomNavigationViewPantallaPrincipal.setSelectedItemId(R.id.Perfil);
            Usuario u = (Usuario) getIntent().getSerializableExtra("Usuario");
            replaceFragment(new PerfilUsuarioFragment(u));
        }
    }

    public void replaceFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("Tipo","Rutinas");
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutPantallaPrincipal, fragment);
        fragmentTransaction.commit();
        if(frameLayoutPantallaPrincipal.getVisibility() == View.GONE) frameLayoutPantallaPrincipal.setVisibility(View.VISIBLE);
        if(viewPager.getVisibility() == View.VISIBLE) viewPager.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            signInAnonymous();
        }
    }

    private Usuario recogerUsuario(String uid) {
        return firestore.getUsuario(uid);
    }

    private void signInAnonymous() {
        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PantallaPrincipal.this, "Signed in Anonymously", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PantallaPrincipal.this, MainActivity.class));
                } else {
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

    private class PagerAdaptador extends FragmentStatePagerAdapter {
        public PagerAdaptador(FragmentManager fm) {
            super(fm);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch(position) {
                case 0:
                    fragment = new CalculadoraMacrosFragment();
                    break;
                case 1:
                    fragment = new CalculadoraRmFragment();
                    break;
                default:
                    fragment = null;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    public static FrameLayout getLayoutSecundario() {
        return layoutSecundario;
    }
}