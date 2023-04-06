package BBDD;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DAO.DaoEjercicio;
import DAO.EjerciciosPorUsuario;
import Entities.Ejercicio;
import Entities.EjerciciosPorRutina;
import Entities.Rutina;
import Entities.Usuarios;

@Database(entities = {Ejercicio.class, Rutina.class, EjerciciosPorRutina.class, EjerciciosPorUsuario.class, Usuarios.class},version = 1,exportSchema = false)
    public abstract class StrenghtBBDD extends RoomDatabase {
        public abstract DaoEjercicio daoEjercicio();
        private static final String DB_NOMBRE = "LearningStrength_BBDD";
        public abstract StrenghtBBDD strenghtBBDD();
        //Hilos necesarios para la escritura de la BBDD permitiendo el uso de la aplicación durante esta tarea.
        private static final int NUMERO_HILOS = 4;
        public static final ExecutorService baseDatosEscritor = Executors.newFixedThreadPool(NUMERO_HILOS);

        //"Patron singleton" con la intención de que el objeto generado por esta clase sea único en toda la aplicación
        private static volatile StrenghtBBDD INSTANCIA;

        public static StrenghtBBDD getBaseDatosStrenght(final Context contexto){
            if (INSTANCIA == null) {
                synchronized (StrenghtBBDD.class){
                    if (INSTANCIA == null)
                        INSTANCIA = Room.databaseBuilder(contexto.getApplicationContext(),
                                        StrenghtBBDD.class,DB_NOMBRE)
                                .addCallback(callBackStrenght).build();
                }
            }
            return INSTANCIA;
        }
        private static RoomDatabase.Callback callBackStrenght = new RoomDatabase.Callback(){
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                baseDatosEscritor.execute(() -> {
                            DaoEjercicio dao = INSTANCIA.daoEjercicio();
                            RellenaEjercicios(INSTANCIA);
                    }
                );
            }
            private void RellenaEjercicios(StrenghtBBDD instancia) {
                instancia.daoEjercicio().insert(new Ejercicio("Sentadilla","Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Press de banca", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Dominadas", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Curl de bíceps", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Extensiones de tríceps", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Peso muerto", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Remo con barra", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Fondos en paralelas", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Prensa de piernas", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Elevación de pantorrillas", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Press militar", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Remo con mancuernas", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Elevaciones laterales", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Curl de martillo", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Fondos en anillas", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Abdominales con rueda", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Desplantes", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Zancadas", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Hip thrust", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Burpees", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Press de pierna", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Crunches de bicicleta", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Curl femoral", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Peso muerto rumano", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Remo con cable", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Flexiones de brazos", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Paseo del granjero", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Press francés", "Monoarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Sentadilla búlgara", "Multiarticular"));
                instancia.daoEjercicio().insert(new Ejercicio("Fondos de tríceps en banco", "Monoarticular"));
            }
        };
    }
