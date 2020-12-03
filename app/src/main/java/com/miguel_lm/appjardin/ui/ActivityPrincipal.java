package com.miguel_lm.appjardin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;
import com.miguel_lm.appjardin.core.RepositorioPlantas;

import java.util.ArrayList;
import java.util.List;

public class ActivityPrincipal extends AppCompatActivity implements SeleccionarPlanta {

   private AdapterPlantas adapterPlantas;
   private List<Planta> listaPlantasEscogidas;

   private TextView textViewNoPlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNoPlantas = findViewById(R.id.textViewNoPlantas);

        guardarPlantas();

        RecyclerView recyclerViewPlantas = findViewById(R.id.recyclerViewPlantas);
        recyclerViewPlantas.setLayoutManager(new GridLayoutManager(this, 2));
        adapterPlantas = new AdapterPlantas(this, this);
        recyclerViewPlantas.setAdapter(adapterPlantas);

        listaPlantasEscogidas = new ArrayList<>();
        comprobarElementos();
    }

    private void guardarPlantas() {

        Planta girasol = new Planta(R.drawable.girasol,"Girasol","","");
        Planta rosa = new Planta(R.drawable.rosa,"Rosa","","");
        Planta aloe = new Planta(R.drawable.aloe,"Aloe Vera","","");
        Planta clavel = new Planta(R.drawable.clavel,"Clavel","","");
        Planta manzanilla = new Planta(R.drawable.manzanilla,"Manzanilla","","");
        Planta dienteDeLeon = new Planta(R.drawable.diente_de_leon,"Diente de León","","");
        Planta cactus = new Planta(R.drawable.cactus,"Cactus","","");
        Planta pampullo = new Planta(R.drawable.pampullo,"Pampullo","","");
        
        RepositorioPlantas repositorioPlantas = RepositorioPlantas.getInstance(this);
        repositorioPlantas.borrarDatos();
        repositorioPlantas.insertar(girasol);
        repositorioPlantas.insertar(rosa);
        repositorioPlantas.insertar(aloe);
        repositorioPlantas.insertar(clavel);
        repositorioPlantas.insertar(manzanilla);
        repositorioPlantas.insertar(dienteDeLeon);
        repositorioPlantas.insertar(cactus);
        repositorioPlantas.insertar(pampullo);

    }

    public void accionInsertarPlantas(View view) {

        insertarPlantasEnRecycler();
    }

    public void insertarPlantasEnRecycler(){

        // Lista de todas las plantas de la BD
        final List<Planta> listaPlantasBD = RepositorioPlantas.getInstance(this).obtenerPlantas();

        // Lista de plantas que se mostrarán en el diálogo (no contendrá las plantas ya seleccionadas)
        final List<Planta> listaPlantasParaDialogo = new ArrayList<>();

        // Eliminar de las plantas de la BD las que ya están escogidas
        for (Planta planta : listaPlantasBD) {

            boolean plantaEncontrada = false;
            for (Planta plantaBD : listaPlantasEscogidas) {

                if (plantaBD.getKey() == planta.getKey()) {
                    plantaEncontrada = true;
                    break;
                }
            }

            if (!plantaEncontrada) {
                listaPlantasParaDialogo.add(planta);
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPrincipal.this);
        builder.setIcon(R.drawable.list);
        builder.setTitle("Añadir a lista");

        String[] arrayPlantas = new String[listaPlantasParaDialogo.size()];
        final boolean[] plantasSeleccionadas = new boolean[listaPlantasParaDialogo.size()];
        for (int i=0; i < listaPlantasParaDialogo.size(); i++)
            arrayPlantas[i] = listaPlantasParaDialogo.get(i).getNombre();
        builder.setMultiChoiceItems(arrayPlantas, plantasSeleccionadas, (dialog, i, isChecked) -> plantasSeleccionadas[i] = isChecked);

        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, int which) {

                AlertDialog.Builder builderConfirmar = new AlertDialog.Builder(ActivityPrincipal.this);
                builderConfirmar.setIcon(R.drawable.exclamation);
                builderConfirmar.setMessage("¿Añadir los elementos?");
                builderConfirmar.setNegativeButton("Cancelar", null);
                builderConfirmar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        for (int i = plantasSeleccionadas.length-1; i>=0; i--) {
                            if (plantasSeleccionadas[i]) {
                                Planta plantaEscogida = listaPlantasParaDialogo.get(i);
                                listaPlantasEscogidas.add(plantaEscogida);
                            }
                        }

                        adapterPlantas.actualizarListado(listaPlantasEscogidas);
                        comprobarElementos();
                        Toast.makeText(ActivityPrincipal.this, "Plantas añadidas", Toast.LENGTH_SHORT).show();
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

        // Dialogo para confirmar borrado

        listaPlantasEscogidas.remove(planta);
        adapterPlantas.actualizarListado(listaPlantasEscogidas);
        comprobarElementos();
    }


    @Override
    public void plantaInfoPulsado(Planta planta) {

        Intent intent = new Intent(this, ActivityDetalle.class);
        startActivity(intent);
    }

    private void comprobarElementos() {

        textViewNoPlantas.setVisibility(listaPlantasEscogidas.isEmpty() ? View.VISIBLE : View.GONE);

    }
}