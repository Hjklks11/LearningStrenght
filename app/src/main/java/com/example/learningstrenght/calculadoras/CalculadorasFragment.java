package com.example.learningstrenght.calculadoras;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.learningstrenght.PantallaPrincipal;
import com.example.learningstrenght.R;
import com.example.learningstrenght.calculadoras.rm.CalculadoraRm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculadorasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculadorasFragment extends Fragment {

    private Button button3, button4, button5, button6, button7, button8, button9, button10, button11,
            button12, button13, button14, button15, button16, button17, button18;

    public CalculadorasFragment() {
        // Required empty public constructor
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        ArrayList<Integer> botones = rellenarNumeros();
        button3.setOnClickListener(view -> {
            if (botones.contains(3))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button4.setOnClickListener(view -> {
            if (botones.contains(4))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button5.setOnClickListener(view -> {
            if (botones.contains(5))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button6.setOnClickListener(view -> {
            if (botones.contains(6))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button7.setOnClickListener(view -> {
            if (botones.contains(7))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button8.setOnClickListener(view -> {
            if (botones.contains(8))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button9.setOnClickListener(view -> {
            if (botones.contains(9))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button10.setOnClickListener(view -> {
            if (botones.contains(10))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button11.setOnClickListener(view -> {
            if (botones.contains(11))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button12.setOnClickListener(view -> {
            if (botones.contains(12))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button13.setOnClickListener(view -> {
            if (botones.contains(13))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button14.setOnClickListener(view -> {
            if (botones.contains(14))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button15.setOnClickListener(view -> {
            if (botones.contains(15))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button16.setOnClickListener(view -> {
            if (botones.contains(16))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button17.setOnClickListener(view -> {
            if (botones.contains(17))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
        button18.setOnClickListener(view -> {
            if (botones.contains(18))
                startActivity(new Intent(getContext(), CalculadoraRm.class));
            else
                Toast.makeText(getContext(), "Ese no es pringao.", Toast.LENGTH_SHORT).show();
        });
    }

    private ArrayList<Integer> rellenarNumeros() {
        ArrayList<Integer> botones = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            botones.add((int) (Math.random() * (18 + 1 - 3)) + 3);
        }

        return botones;
    }

    public static CalculadorasFragment newInstance() {
        CalculadorasFragment fragment = new CalculadorasFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calculadoras, container, false);
    }
}