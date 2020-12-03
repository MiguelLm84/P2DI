package com.miguel_lm.appjardin.ui;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;

public class ViewHolderPlanta extends RecyclerView.ViewHolder{

    private final ImageView imagenView;
    private final TextView tvNombre;
    private final LinearLayout linearLayoutItemPlanta;

    private final SeleccionarPlanta seleccionarPlanta;

    public ViewHolderPlanta(@NonNull View itemView, SeleccionarPlanta selecPlanta) {
        super(itemView);

        this.seleccionarPlanta = selecPlanta;

        imagenView = itemView.findViewById(R.id.imagenPlanta);
        tvNombre = itemView.findViewById(R.id.tvNombrePlanta);

        linearLayoutItemPlanta = itemView.findViewById(R.id.linearLayoutPlanta);
    }

    public void mostrarPlanta(final Planta planta) {

        imagenView.setImageResource(planta.getImagen());
        tvNombre.setText(planta.getNombre());

        linearLayoutItemPlanta.setOnClickListener(v -> seleccionarPlanta.plantaInfoPulsado(planta));
        linearLayoutItemPlanta.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                seleccionarPlanta.eliminarPlanta(planta);

                return false;

            }
        });
    }
}
