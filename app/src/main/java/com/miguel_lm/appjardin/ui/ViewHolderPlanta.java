package com.miguel_lm.appjardin.ui;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;

public class ViewHolderPlanta extends RecyclerView.ViewHolder{

    private ImageView imagenView;
    private EditText edNombre;
    private final LinearLayout linearLayoutItemPlanta;

    private SeleccionarPlanta seleccionarPlanta;

    public ViewHolderPlanta(@NonNull View itemView, SeleccionarPlanta selecPlanta) {
        super(itemView);

        this.seleccionarPlanta = selecPlanta;

        imagenView = itemView.findViewById(R.id.imagenPlanta);
        edNombre = itemView.findViewById(R.id.tvNombrePlanta);

        linearLayoutItemPlanta = itemView.findViewById(R.id.constraintLayoutPlanta);
    }

    public void mostrarPlanta(final Planta planta, final Context context) {

        imagenView.setBackground(planta.getImagen());
        edNombre.setText(planta.getNombre());

        linearLayoutItemPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seleccionarPlanta.plantaInfoPulsado(planta);

            }
        });
    }
}
