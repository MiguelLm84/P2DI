package com.miguel_lm.appjardin.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;

import com.miguel_lm.appjardin.R;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_planta);

        new Handler().postDelayed(() -> startActivity(new Intent(ActivitySplash.this, ActivityPrincipal.class)), 1500);

        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
    }
}