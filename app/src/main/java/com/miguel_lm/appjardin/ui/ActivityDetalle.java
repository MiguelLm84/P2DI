package com.miguel_lm.appjardin.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.miguel_lm.appjardin.R;
import com.miguel_lm.appjardin.core.Planta;

public class ActivityDetalle extends AppCompatActivity {

    private Planta planta;

    public static final String CLAVE_PLANTA = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        planta = (Planta) getIntent().getSerializableExtra(CLAVE_PLANTA);
        seleccionPlantaInfo();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    private void seleccionPlantaInfo() {

        ImageView imagenPlanta = findViewById(R.id.imageViewPlanta);
        TextView tvTitulo = findViewById(R.id.tvNombre_InfoPlanta);
        TextView tvNomComunPlanta = findViewById(R.id.tvNomComunPlanta);
        TextView tvNomCientificoPlanta = findViewById(R.id.tvNomCientificoPlanta);
        TextView tvNomTemporadaPlanta = findViewById(R.id.tvTemporadaPlanta);
        TextView tvDescripcion = findViewById(R.id.tvDescripcion);

        int imagenId = getResources().getIdentifier(planta.getImagen(), "drawable", getPackageName());

        imagenPlanta.setImageResource(imagenId);
        tvTitulo.setText(planta.getNombre());
        tvNomComunPlanta.setText(getString(R.string.titulo_nombre_comun) + " " +  planta.getNombre());
        tvNomCientificoPlanta.setText(getString(R.string.titulo_nombre_cientifico) + " " +  planta.getNombreCientifico());
        tvNomTemporadaPlanta.setText(getString(R.string.titulo_temporada) + " " + planta.getTemporada());
        tvDescripcion.setText(planta.getDescripcion());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}