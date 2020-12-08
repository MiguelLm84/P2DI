package com.miguel_lm.appjardin.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
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

        imagenView.setImageResource(planta.getImagen());
        tvNombre.setText(planta.getNombre());

        linearLayoutItemPlanta.setOnClickListener(v -> seleccionarPlanta.plantaInfoPulsado(planta));
        linearLayoutItemPlanta.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final PopupMenu popupMenu = new PopupMenu(context, linearLayoutItemPlanta);
                popupMenu.getMenuInflater().inflate(R.menu.menu_contextual_planta, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.accionEliminar:
                                seleccionarPlanta.eliminarPlanta(planta);
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();

                return false;





                //seleccionarPlanta.eliminarPlanta(planta);

                //return false;

            }
        });
    }
}
