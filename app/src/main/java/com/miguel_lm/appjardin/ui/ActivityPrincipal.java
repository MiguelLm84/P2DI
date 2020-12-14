package com.miguel_lm.appjardin.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;
import com.miguel_lm.appjardin.core.RepositorioPlantas;

import java.util.ArrayList;
import java.util.List;

import static com.miguel_lm.appjardin.ui.ActivityDetalle.CLAVE_PLANTA;

public class ActivityPrincipal extends AppCompatActivity implements SeleccionarPlanta {

    private AdapterPlantas adapterPlantas;
    private List<Planta> listaPlantasEscogidas;
    private long tiempoParaSalir = 0;
    private TextView textViewNoPlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        
        textViewNoPlantas = findViewById(R.id.textViewNoPlantas);

        guardarPlantas();

        RecyclerView recyclerViewPlantas = findViewById(R.id.recyclerViewPlantas);
        recyclerViewPlantas.setLayoutManager(new GridLayoutManager(this, 2));
        adapterPlantas = new AdapterPlantas(this, this);
        recyclerViewPlantas.setAdapter(adapterPlantas);

        listaPlantasEscogidas = RepositorioPlantas.getInstance(this).obtenerPlantasSeleccionadas();

        adapterPlantas.actualizarListado(listaPlantasEscogidas);

        comprobarElementos();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal_planta, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.accionEliminar) {
            accionEliminar();
        }
        if (item.getItemId() == R.id.accionAnhadirPlanta) {
            insertarPlantasEnRecycler();
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardarPlantas() {

        Planta girasol = new Planta("girasol", getString(R.string.nomComunGirasol), getString(R.string.nomCientificoGirasol), getString(R.string.temporadaGirasol), getString(R.string.descripcionGirasol), 0);
        Planta rosa = new Planta("rosa", getString(R.string.nomComunRosa), getString(R.string.nomCientificoRosa), getString(R.string.temporadaRosa), getString(R.string.descripcionRosa), 0);
        Planta aloe = new Planta("aloe", getString(R.string.nomComunAloe), getString(R.string.nomCientificoAloe), getString(R.string.temporadaAloe), getString(R.string.descripcionAloe), 0);
        Planta clavel = new Planta("clavel", getString(R.string.nomComunClavel), getString(R.string.nomCientificoClavel), getString(R.string.temporadaClavel), getString(R.string.descripcionClavel), 0);
        Planta manzanilla = new Planta("manzanilla", getString(R.string.nomComunManzanilla), getString(R.string.nomCientificoManzanilla), getString(R.string.temporadaManzanilla), getString(R.string.descripcionManzanilla), 0);
        Planta diente_de_leon = new Planta("diente_de_leon", getString(R.string.nomComunDienteLeon), getString(R.string.nomCientificoDienteLeon), getString(R.string.temporadaDienteLeon), getString(R.string.descripcionDienteLeon), 0);
        Planta cactus = new Planta("cactus", getString(R.string.nomComunCactus), getString(R.string.nomCientificoCactus), getString(R.string.temporadaCactus), getString(R.string.descripcionCactus), 0);
        Planta crisantemo = new Planta("crisantemo", getString(R.string.nomComunCrisantemo), getString(R.string.nomCientificoCrisantemo), getString(R.string.temporadaCrisantemo), getString(R.string.descripcionCrisantemo), 0);

        RepositorioPlantas repositorioPlantas = RepositorioPlantas.getInstance(this);
        repositorioPlantas.insertar(girasol);
        repositorioPlantas.insertar(rosa);
        repositorioPlantas.insertar(aloe);
        repositorioPlantas.insertar(clavel);
        repositorioPlantas.insertar(manzanilla);
        repositorioPlantas.insertar(diente_de_leon);
        repositorioPlantas.insertar(cactus);
        repositorioPlantas.insertar(crisantemo);

    }

    public void accionInsertarPlantas(View view) {

        insertarPlantasEnRecycler();
    }

    public void insertarPlantasEnRecycler() {

        final List<Planta> listaPlantasBD = RepositorioPlantas.getInstance(this).obtenerPlantas();
        final List<Planta> listaPlantasParaDialogo = new ArrayList<>();
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

        if (listaPlantasParaDialogo.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPrincipal.this);
            builder.setIcon(R.drawable.list);
            builder.setTitle("Añadir a lista");
            builder.setMessage("Lista vacia, no hay plantas seleccionables.");
            builder.setPositiveButton("Entendido", null);
            builder.show();

            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityPrincipal.this);
        builder.setIcon(R.drawable.list);
        builder.setTitle("Añadir a lista");

        String[] arrayPlantas = new String[listaPlantasParaDialogo.size()];
        final boolean[] plantasSeleccionadas = new boolean[listaPlantasParaDialogo.size()];
        for (int i = 0; i < listaPlantasParaDialogo.size(); i++)
            arrayPlantas[i] = listaPlantasParaDialogo.get(i).getNombre();
        builder.setMultiChoiceItems(arrayPlantas, plantasSeleccionadas, (dialog, i, isChecked) -> plantasSeleccionadas[i] = isChecked);

        builder.setPositiveButton("Añadir", (dialog, which) -> {

            if(!listaPlantasParaDialogo.isEmpty()){

                for (int i = plantasSeleccionadas.length - 1; i >= 0; i--) {
                    if (plantasSeleccionadas[i]) {

                        Planta plantaEscogida = listaPlantasParaDialogo.get(i);

                        plantaEscogida.setSeleccionada(1);
                        RepositorioPlantas.getInstance(ActivityPrincipal.this).actualizarPlanta(plantaEscogida);

                        listaPlantasEscogidas.add(plantaEscogida);
                    }
                }
            } else{
                builder.setMessage("Lista vacia, no se puede seleccionar más plantas.");
            }

            adapterPlantas.actualizarListado(listaPlantasEscogidas);
            comprobarElementos();
            Toast.makeText(ActivityPrincipal.this, "Plantas añadidas", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }

    @Override
    public void eliminarPlanta(Planta planta) {

        // Dialogo para confirmar borrado

        planta.setSeleccionada(0);
        RepositorioPlantas.getInstance(ActivityPrincipal.this).actualizarPlanta(planta);

        listaPlantasEscogidas.remove(planta);
        adapterPlantas.actualizarListado(listaPlantasEscogidas);
        comprobarElementos();
    }


    @Override
    public void plantaInfoPulsado(Planta planta) {

        Intent intent = new Intent(this, ActivityDetalle.class);
        intent.putExtra(CLAVE_PLANTA, planta);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back);
    }

    private void comprobarElementos() {

        textViewNoPlantas.setVisibility(listaPlantasEscogidas.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void accionEliminar() {


        AlertDialog.Builder builderElimina = new AlertDialog.Builder(ActivityPrincipal.this);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        builderElimina.setIcon(R.drawable.remove_symbol);
        builderElimina.setTitle("Eliminar");

        String[] arrayPlantas = new String[listaPlantasEscogidas.size()];
        final boolean[] plantasSeleccionados = new boolean[listaPlantasEscogidas.size()];
        for (int i = 0; i < listaPlantasEscogidas.size(); i++)
            arrayPlantas[i] = listaPlantasEscogidas.get(i).getNombre();
        builderElimina.setMultiChoiceItems(arrayPlantas, plantasSeleccionados, (dialog, i, isChecked) -> plantasSeleccionados[i] = isChecked);

        builderElimina.setPositiveButton("Borrar", (dialog, which) -> {

            AlertDialog.Builder builderEliminar_Confirmar = new AlertDialog.Builder(ActivityPrincipal.this);
            builderEliminar_Confirmar.setIcon(R.drawable.exclamation);
            builderEliminar_Confirmar.setMessage("¿Eliminar los elementos?");
            builderEliminar_Confirmar.setNegativeButton("Cancelar", null);
            builderEliminar_Confirmar.setPositiveButton("Borrar", (dialogInterface, which1) -> {

                for (int i = listaPlantasEscogidas.size() - 1; i >= 0; i--) {
                    if (plantasSeleccionados[i]) {

                        Planta plantaEscogida = listaPlantasEscogidas.get(i);

                        plantaEscogida.setSeleccionada(0);
                        RepositorioPlantas.getInstance(ActivityPrincipal.this).actualizarPlanta(plantaEscogida);

                        listaPlantasEscogidas.remove(i);
                    }
                }
                adapterPlantas.actualizarListado(listaPlantasEscogidas);
                comprobarElementos();

                Toast.makeText(ActivityPrincipal.this, "Plantas eliminadas", Toast.LENGTH_SHORT).show();
            });
            builderEliminar_Confirmar.create().show();
        });
        builderElimina.setNegativeButton("Cancelar", null);
        builderElimina.create().show();
    }

    @Override
    public void onBackPressed(){

        long tiempo = System.currentTimeMillis();
        if (tiempo - tiempoParaSalir > 3000) {
            tiempoParaSalir = tiempo;
            Toast.makeText(this, "Presione de nuevo 'Atrás' si desea salir",
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}