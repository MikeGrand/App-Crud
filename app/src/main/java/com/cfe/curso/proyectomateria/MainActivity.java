package com.cfe.curso.proyectomateria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnCrear = findViewById(R.id.btnCrear);

        Button btnModicar = findViewById(R.id.btnModicar);
        Button btnEliminar = findViewById(R.id.btnEliminar);
        Button btnMostrar = findViewById(R.id.btnMostrar);

        btnCrear.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_crear.class);
            startActivity(intent);
        });

        btnModicar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_modifcar.class);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_eliminar.class);
            startActivity(intent);
        });

        btnMostrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_mostraractivity
                    .class);
            startActivity(intent);
        });

    }
}