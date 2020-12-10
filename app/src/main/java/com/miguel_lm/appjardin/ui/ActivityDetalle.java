package com.miguel_lm.appjardin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;
import com.miguel_lm.appjardin.core.RepositorioPlantas;

import java.util.List;

public class ActivityDetalle extends AppCompatActivity {

    private Planta planta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    public void seleccionPlantaInfo(Planta planta){

        List<Planta> listaPlantas = RepositorioPlantas.getInstance(ActivityDetalle.this).obtenerPlantas();

        ImageView imagenPlanta = findViewById(R.id.imageViewPlanta);
        TextView tvTitulo=findViewById(R.id.tvNombre_InfoPlanta);
        TextView tvNomComunPlanta=findViewById(R.id.tvNomComunPlanta);
        TextView tvNomCintificoPlanta=findViewById(R.id.tvNomCientificoPlanta);
        TextView tvNomTemporadaPlanta=findViewById(R.id.tvTemporadaPlanta);

        for(int i=0;i<listaPlantas.size();i++){
            if(listaPlantas.get(i).getNombre()==planta.getNombre()){
                if(planta.getNombre()=="girasol"){
                    imagenPlanta.setImageResource(R.drawable.girasol_info);
                    tvTitulo.setText(R.string.girasol_titulo_info);
                    tvNomComunPlanta.setText(R.string.nomComunGirasol);
                    tvNomCintificoPlanta.setText(R.string.nomCientificoGirasol);
                    tvNomTemporadaPlanta.setText(R.string.temporadaGirasol);
                }
            }
        }
    }
}