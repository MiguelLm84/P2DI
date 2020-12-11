package com.miguel_lm.appjardin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;
import com.miguel_lm.appjardin.core.RepositorioPlantas;

import java.util.ArrayList;
import java.util.List;

import static com.miguel_lm.appjardin.ui.ActivityDetalle.CLAVE_PLANTA;

public class ActivityPrincipal extends AppCompatActivity implements SeleccionarPlanta {

    private AdapterPlantas adapterPlantas;
    private List<Planta> listaPlantasEscogidas;

    private TextView textViewNoPlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        return super.onOptionsItemSelected(item);
    }

    private void guardarPlantas() {

        Planta girasol = new Planta("girasol", getString(R.string.nomComunGirasol), getString(R.string.nomCientificoGirasol), getString(R.string.temporadaGirasol), getString(R.string.descripcionGirasol), 0);
        Planta rosa = new Planta("rosa", getString(R.string.nomComunRosa), getString(R.string.nomCientificoRosa), getString(R.string.temporadaRosa), getString(R.string.descripcionRosa), 0);
        Planta aloe = new Planta("aloe", getString(R.string.nomComunAloe), getString(R.string.nomCientificoAloe), getString(R.string.temporadaAloe), getString(R.string.descripcionAloe), 0);
        Planta clavel = new Planta("clavel", getString(R.string.nomComunClavel), getString(R.string.nomCientificoClavel), getString(R.string.temporadaClavel), getString(R.string.descripcionClavel), 0);
        Planta manzanilla = new Planta("manzanilla", getString(R.string.nomComunManzanilla), getString(R.string.nomCientificoManzanilla), getString(R.string.temporadaManzanilla), getString(R.string.descripcionManzanilla), 0);
        Planta dienteDeLeon = new Planta("dienteDeLeon", getString(R.string.nomComunDienteLeon), getString(R.string.nomCientificoDienteLeon), getString(R.string.temporadaDienteLeon), getString(R.string.descripcionDienteLeon), 0);
        Planta cactus = new Planta("cactus", getString(R.string.nomComunCactus), getString(R.string.nomCientificoCactus), getString(R.string.temporadaCactus), getString(R.string.descripcionCactus), 0);
        Planta crisantemo = new Planta("crisantemo", getString(R.string.nomComunCrisantemo), getString(R.string.nomCientificoCrisantemo), getString(R.string.temporadaCrisantemo), getString(R.string.descripcionCrisantemo), 0);

        RepositorioPlantas repositorioPlantas = RepositorioPlantas.getInstance(this);
        repositorioPlantas.insertar(girasol);
        repositorioPlantas.insertar(rosa);
        repositorioPlantas.insertar(aloe);
        repositorioPlantas.insertar(clavel);
        repositorioPlantas.insertar(manzanilla);
        repositorioPlantas.insertar(dienteDeLeon);
        repositorioPlantas.insertar(cactus);
        repositorioPlantas.insertar(crisantemo);

    }

    public void accionInsertarPlantas(View view) {

        insertarPlantasEnRecycler();
    }

    public void insertarPlantasEnRecycler() {

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
        for (int i = 0; i < listaPlantasParaDialogo.size(); i++)
            arrayPlantas[i] = listaPlantasParaDialogo.get(i).getNombre();
        builder.setMultiChoiceItems(arrayPlantas, plantasSeleccionadas, (dialog, i, isChecked) -> plantasSeleccionadas[i] = isChecked);

        builder.setPositiveButton("Añadir", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, int which) {

                for (int i = plantasSeleccionadas.length - 1; i >= 0; i--) {
                    if (plantasSeleccionadas[i]) {

                        Planta plantaEscogida = listaPlantasParaDialogo.get(i);

                        // Marcar a 1 el campo 'seleccionado' y guardarlo en la BD
                        plantaEscogida.setSeleccionada(1);
                        RepositorioPlantas.getInstance(ActivityPrincipal.this).actualizarPlanta(plantaEscogida);

                        listaPlantasEscogidas.add(plantaEscogida);
                    }
                }

                adapterPlantas.actualizarListado(listaPlantasEscogidas);
                comprobarElementos();
                Toast.makeText(ActivityPrincipal.this, "Plantas añadidas", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.create().show();
    }

    @Override
    public void eliminarPlanta(Planta planta) {

        // Dialogo para confirmar borrado

        // Marcar a 0 el campo 'seleccionado' y guardarlo en la BD
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

        //intent.putExtra(CLAVE_PLANTA, planta.getKey());

        startActivity(intent);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back);
        //Planta planta = new Planta();
    }

    private void comprobarElementos() {

        textViewNoPlantas.setVisibility(listaPlantasEscogidas.isEmpty() ? View.VISIBLE : View.GONE);

    }

    private void accionEliminar() {

        final List<Planta> listaPlantas = RepositorioPlantas.getInstance(this).obtenerPlantas();

        AlertDialog.Builder builderElimina = new AlertDialog.Builder(ActivityPrincipal.this);
        builderElimina.setIcon(R.drawable.remove_symbol);
        builderElimina.setTitle("Eliminar");

        String[] arrayEntrenamientos = new String[listaPlantas.size()];
        final boolean[] plantasSeleccionados = new boolean[listaPlantas.size()];
        for (int i = 0; i < listaPlantas.size(); i++)
            arrayEntrenamientos[i] = listaPlantas.get(i).getNombre();
        builderElimina.setMultiChoiceItems(arrayEntrenamientos, plantasSeleccionados, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                plantasSeleccionados[i] = isChecked;
            }
        });

        builderElimina.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {

            List<Planta> listaPlantas = RepositorioPlantas.getInstance(ActivityPrincipal.this).obtenerPlantas();

            @Override
            public void onClick(final DialogInterface dialog, int which) {

                AlertDialog.Builder builderEliminar_Confirmar = new AlertDialog.Builder(ActivityPrincipal.this);
                builderEliminar_Confirmar.setIcon(R.drawable.exclamation);
                builderEliminar_Confirmar.setMessage("¿Eliminar los elementos?");
                builderEliminar_Confirmar.setNegativeButton("Cancelar", null);
                builderEliminar_Confirmar.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        for (int i = 0; i < listaPlantas.size(); i++)
                            if (plantasSeleccionados[i])
                                RepositorioPlantas.getInstance(ActivityPrincipal.this).eliminarPlanta(listaPlantas.get(i));

                        for (int i = listaPlantas.size() - 1; i >= 0; i--) {
                            if (plantasSeleccionados[i]) {
                                listaPlantas.remove(i);
                            }
                        }
                        ActivityPrincipal.this.adapterPlantas.notifyDataSetChanged();
                        adapterPlantas.actualizarListado(listaPlantas);
                        Toast.makeText(ActivityPrincipal.this, "Entrenamiento eliminado", Toast.LENGTH_SHORT).show();
                    }
                });
                builderEliminar_Confirmar.create().show();
            }
        });
        builderElimina.setNegativeButton("Cancelar", null);
        builderElimina.create().show();
    }
}