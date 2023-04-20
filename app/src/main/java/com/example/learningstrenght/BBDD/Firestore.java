package com.example.learningstrenght.BBDD;

import androidx.annotation.NonNull;

import com.example.learningstrenght.Entidades.Ejercicio;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Firestore {
        private FirebaseAuth auth;
        private FirebaseFirestore firestore;
        static private Firestore INSTANCIA;
        private Firestore(){
            firestore = FirebaseFirestore.getInstance();
        }
        static public Firestore getInstance(){
            if(INSTANCIA==null)
                INSTANCIA = new Firestore();
            return INSTANCIA;
        }
        public void InsertarEjercicio(Ejercicio ejercicio){
            Map<String,Object> map = new HashMap<>();
            map.put("grupoMuscular",ejercicio.getGrupoMuscular());
            map.put("nombreEjercicio",ejercicio.getNombreEjercicio());
            map.put("tipoEjercicio",ejercicio.getTipoEjercicio());
            map.put("tipoEntrenamiento",ejercicio.getTipoEntrenamiento());
            firestore.collection("Ejercicio").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    System.out.println(ejercicio.toString() + " añdido");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("error al insertar " + ejercicio.toString());
                }
            });
        }
}
