package com.example.learningstrenght.calculadoras;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.learningstrenght.PantallaPrincipal;
import com.example.learningstrenght.R;
import com.example.learningstrenght.calculadoras.macros.CalculadoraMacrosActivity;
import com.example.learningstrenght.calculadoras.rm.CalculadoraRmActivity;

public class CalculadorasFragment extends Fragment {

    private Button btnRm, btnMacros;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculadoras, container, false);
        inicializarCalculadoras(view);

        return view;
    }

    private void inicializarCalculadoras(View view) {
        btnRm = view.findViewById(R.id.btnCalculadoraRmFragmentCalculadoras);
        btnMacros = view.findViewById(R.id.btnCalculadoraMacrosFragmentCalculadoras);

        btnRm.setOnClickListener(view1 -> startActivity(new Intent(getContext(), CalculadoraRmActivity.class)));
        btnMacros.setOnClickListener(view1 -> startActivity(new Intent(getContext(), CalculadoraMacrosActivity.class)));
    }

    private void cerrarAplicacion(){
        Intent intent = new Intent(getContext(), PantallaPrincipal.class);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public static CalculadorasFragment newInstance() {
        CalculadorasFragment fragment = new CalculadorasFragment();
        Bundle args = new Bundle();
        return fragment;
    }

}