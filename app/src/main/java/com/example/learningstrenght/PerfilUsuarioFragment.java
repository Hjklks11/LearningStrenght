package com.example.learningstrenght;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class PerfilUsuarioFragment extends Fragment {
    private LinearLayout layoutPrincipal;
    private ShapeableImageView fotoPerfil;
    private MaterialTextView txtUsuario, txtNombre, txtCorreo, txtFechaNac, txtPeso, txtAltura;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_usuario, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        inicializarComponentes(view);

        if (user.isAnonymous()) {
            layoutPrincipal = view.findViewById(R.id.linearLayoutFragmentPerfilUsuario);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                layoutPrincipal.setForeground(Drawable.createFromPath("@drawable/cbumperro"));
            }
        } else {
            txtUsuario.setText(user.getDisplayName());
            txtNombre.setText("RevientaAbuelas69");
            txtCorreo.setText(user.getEmail());
            txtFechaNac.setText(user.getMetadata().toString());
            txtPeso.setText("Curvado");
            txtAltura.setText("Enano");
        }

        return view;
    }

    private void inicializarComponentes(View view) {
        layoutPrincipal = view.findViewById(R.id.linearLayoutFragmentPerfilUsuario);
        fotoPerfil = view.findViewById(R.id.fotoFragmentPerfilUsuario);
        txtUsuario = view.findViewById(R.id.tilNombreUsuarioFragmentPerfilUsuario);
        txtNombre = view.findViewById(R.id.tilNombreFragmentPerfilUsuario);
        txtCorreo = view.findViewById(R.id.tilEmailFragmentPerfilUsuario);
        txtFechaNac = view.findViewById(R.id.tilFechaNacFragmentPerfilusuario);
        txtPeso = view.findViewById(R.id.tilPesoFragmentPerfilusuario);
        txtAltura = view.findViewById(R.id.tilAlturaFragmentPerfilusuario);
    }
}