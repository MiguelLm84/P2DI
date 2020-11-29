package com.miguel_lm.appjardin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;
import com.miguel_lm.appjardin.core.RepositorioPlantas;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SeleccionarPlanta {

    AdapterPlantas adapterPlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewPlantas = findViewById(R.id.recyclerViewPlantas);
        recyclerViewPlantas.setLayoutManager(new LinearLayoutManager(this));
        adapterPlantas = new AdapterPlantas(this, this);
        recyclerViewPlantas.setAdapter(adapterPlantas);


    }

    public void accionInsertarPlantas(View view) {

        insertarPlantasEnRecycler();
    }

    public void insertarPlantasEnRecycler(){

        final List<Planta> listaPlantas = RepositorioPlantas.getInstance(this).obtenerPlantas();

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.list);
        builder.setTitle("Añadir a lista");

        String[] arrayPlantas = new String[listaPlantas.size()];
        final boolean[] plantasSeleccionadas = new boolean[listaPlantas.size()];
        for (int i=0; i < listaPlantas.size(); i++)
            arrayPlantas[i] = listaPlantas.get(i).getImagen() + ", " + listaPlantas.get(i).getNombre();
        builder.setMultiChoiceItems(arrayPlantas, plantasSeleccionadas, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                plantasSeleccionadas[i] = isChecked;
            }
        });

        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {

            List<Planta> listaPlantas = RepositorioPlantas.getInstance(MainActivity.this).obtenerPlantas();

            @Override
            public void onClick(final DialogInterface dialog, int which) {

                AlertDialog.Builder builderConfirmar = new AlertDialog.Builder(MainActivity.this);
                builderConfirmar.setIcon(R.drawable.exclamation);
                builderConfirmar.setMessage("¿Añadir los elementos?");
                builderConfirmar.setNegativeButton("Cancelar", null);
                builderConfirmar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        for (int i = listaPlantas.size()-1; i>=0; i--) {
                            if (plantasSeleccionadas[i]) {
                                Planta plantaAnhadida = new Planta(listaPlantas.get(i).getImagen(),listaPlantas.get(i).getNombre());
                            }
                        }
                        MainActivity.this.adapterPlantas.notifyDataSetChanged();
                        adapterPlantas.actualizarListado();
                        Toast.makeText(MainActivity.this, "Plantas añadidas", Toast.LENGTH_SHORT).show();
                    }
                });
                builderConfirmar.create().show();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }

    @Override
    public void eliminarPlanta(Planta planta) {

    }

    @Override
    public void modificarPlanta(Planta planta) {

    }

    @Override
    public void plantaInfoPulsado(Planta planta) {

    }
}