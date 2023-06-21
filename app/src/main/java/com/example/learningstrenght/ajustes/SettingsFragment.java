package com.example.learningstrenght.ajustes;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.learningstrenght.R;
import com.example.learningstrenght.baseDeDatos.Firestore;
import com.example.learningstrenght.login.MainActivity;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends PreferenceFragmentCompat {
    private SwitchPreference switchModoOscuro;
    private ListPreference listaCalculadoras;
    private EditTextPreference etCambiarCorreo;
    private Preference dialogEliminarCuenta, cambiarPass;
    private Firestore firestore;
    private FirebaseAuth mAuth;
    public static final String mPrefsName = "mPrefs";
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        sharedPreferences = getContext().getSharedPreferences(mPrefsName, MODE_PRIVATE);
        editor = getContext().getSharedPreferences(mPrefsName, MODE_PRIVATE).edit();
        firestore = Firestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        inicializarComponentes();
        listenersAplicacion();
        listenersCuenta();
    }

    private void inicializarComponentes() {
        switchModoOscuro = findPreference("switchModoOscuro");
        listaCalculadoras = findPreference("listaCalculadoras");
        etCambiarCorreo = findPreference("etCambiarCorreo");
        cambiarPass = findPreference("CambiarContrasenia");
        dialogEliminarCuenta = findPreference("preferenceEliminarCuenta");
        etCambiarCorreo.setText(firestore.getUsuario().getCorreo());
    }

    private void listenersAplicacion() {
        switchModoOscuro.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {
                if (switchModoOscuro.isChecked()) {
                    System.out.println("Activando modo oscuro");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("switchModoOscuro", true);
                    //recreate();
                } else {
                    System.out.println("Desactivando modo oscuro");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("switchModoOscuro", false);
                    //recreate();
                }
                editor.apply();
                return true;
            }
        });
        listaCalculadoras.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                switch (listaCalculadoras.findIndexOfValue((String) newValue)) {
                    case 0:
                        System.out.println("Calculadora Macros");
                        editor.putString("listaCalculadoras", "Macros");
                        break;
                    case 1:
                        System.out.println("Calculadora Rm");
                        editor.putString("listaCalculadoras", "Rm");
                        break;
                }
                editor.apply();
                return true;
            }
        });
    }

    private void listenersCuenta() {
        etCambiarCorreo.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                System.out.println("Nuevo correo: " + newValue);

                mAuth.getCurrentUser().updateEmail((String) newValue);
                mAuth.getCurrentUser().sendEmailVerification();
                firestore.actualizarUsuario(firestore.getUsuario(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                mAuth.signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("Mensaje", "Por favor, verifica el correo antes de iniciar sesion.");
                startActivity(intent);
                return true;
            }
        });

        cambiarPass.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Introduce tu contraseña actual:");
                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);
                builder.setPositiveButton("Aceptar", (dialog, which) -> {
                    String pass = input.getText().toString().trim();
                    AuthCredential credential = EmailAuthProvider.getCredential(mAuth.getCurrentUser().getEmail(), pass);
                    mAuth.getCurrentUser().reauthenticate(credential).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            cambiarPassEmail();
                        } else {
                            Toast.makeText(getContext(), "Contraseña erronea", Toast.LENGTH_SHORT).show();
                            cambiarPass.performClick();
                        }
                    });
                });
                builder.setNegativeButton("No me acuerdo de la contraseña", (dialog, which) -> {
                    mAuth.sendPasswordResetEmail(mAuth.getCurrentUser().getEmail());
                    Toast.makeText(getContext(), "Te hemos enviado un correo para reestablecer tu contraseña", Toast.LENGTH_SHORT).show();
                });
                builder.show();
                return true;
            }
        });

        dialogEliminarCuenta.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference) {
                // Mostrar confirmacion
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Eliminar cuenta definitivamente");
                builder.setMessage("¿Estas seguro de que deseas eliminar tu cuenta y todos tus datos definitivamente?");
                builder.setPositiveButton("Si, soy un flaco", (dialogInterface, i) -> {
                    firestore.borrarUsuario(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    mAuth.getCurrentUser().delete();
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                });
                builder.setNegativeButton("No, estoy mas fuerte que el vinagre", (dialogInterface, i) ->
                        Toast.makeText(getContext(), "Asi me gusta", Toast.LENGTH_SHORT).show());
                builder.show();
                return true;
            }
        });
    }

    private void cambiarPassEmail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Introduce tu nueva contraseña:");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            String pass = input.getText().toString().trim();
            if (pass.isBlank()) {
                cambiarPassEmail();
            } else {
                mAuth.getCurrentUser().updatePassword(pass);
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

}