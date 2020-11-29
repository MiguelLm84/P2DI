package com.miguel_lm.appjardin.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;
import com.miguel_lm.appjardin.core.RepositorioPlantas;

import java.util.List;

public class AdapterPlantas extends RecyclerView.Adapter<ViewHolderPlanta> {

    private List<Planta> listPlanta;
    private final Context context;
    private final SeleccionarPlanta selecPlanta;

    public AdapterPlantas(final Context context, SeleccionarPlanta selecPlanta){
        this.listPlanta = RepositorioPlantas.getInstance(context).obtenerPlantas();
        this.context = context;
        this.selecPlanta = selecPlanta;
    }

    public void actualizarListado() {
        this.listPlanta = RepositorioPlantas.getInstance(context).obtenerPlantas();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderPlanta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.planta_card_view, parent, false);
        return new ViewHolderPlanta(v, selecPlanta);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlanta holder, int position) {

        Planta plantaAPintar = listPlanta.get(position);
        holder.mostrarPlanta(plantaAPintar,context);
    }

    @Override
    public int getItemCount() {
        return listPlanta.size();
    }
}
