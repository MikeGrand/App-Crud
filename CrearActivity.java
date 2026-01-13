package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearActivity extends AppCompatActivity {

    EditText txtNom;
    EditText txtApp;
    EditText txtTel;

    Button btnAceptar;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear);

        txtNom = (EditText) findViewById(R.id.txtNom);
        txtApp = (EditText) findViewById(R.id.txtApp);
        txtTel = (EditText) findViewById(R.id.txtTel);

        btnAceptar = (Button) findViewById(R.id.btnCrear);

        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { enviar(view);}
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish();}
        });
    }
}