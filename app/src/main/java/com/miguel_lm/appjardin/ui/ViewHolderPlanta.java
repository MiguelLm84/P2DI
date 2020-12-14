package com.miguel_lm.appjardin.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    public void mostrarPlanta(final Planta planta, final Context context) {

        int imagenId = context.getResources().getIdentifier(planta.getImagen(), "drawable", context.getPackageName());

        imagenView.setImageResource(imagenId);
        tvNombre.setText(planta.getNombre());

        linearLayoutItemPlanta.setOnClickListener(v -> seleccionarPlanta.plantaInfoPulsado(planta));
        linearLayoutItemPlanta.setOnLongClickListener(v -> {

            AlertDialog.Builder builderEliminar_Confirmar = new AlertDialog.Builder(context);
            builderEliminar_Confirmar.setIcon(R.drawable.exclamation);
            builderEliminar_Confirmar.setMessage("¿Eliminar los elementos?");
            builderEliminar_Confirmar.setNegativeButton("Cancelar", null);
            builderEliminar_Confirmar.setPositiveButton("Borrar", (dialogInterface, which) -> {
                seleccionarPlanta.eliminarPlanta(planta);
                //comprobarElementos();
                Toast.makeText(context, "Planta eliminada", Toast.LENGTH_SHORT).show();
            });
            builderEliminar_Confirmar.create().show();
            return false;
        });
    }
}
