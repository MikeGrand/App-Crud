package com.cfe.curso.proyectomateria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class activity_eliminar extends AppCompatActivity {

    EditText txtId;
    Button btnEliminar, btnRegistrar;

    // CONFIGURACIÓN CON TU IP REAL
    String BASE_URL = "http://172.26.162.192/serv/index.php";
    String MY_KEY = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtId = findViewById(R.id.txtId);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarContacto();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void eliminarContacto() {
        String id = txtId.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "Debes escribir el ID a eliminar", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = BASE_URL + "?tipo=4&id=" + id + "&llave=" + MY_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(activity_eliminar.this, "Eliminado con éxito", Toast.LENGTH_SHORT).show();
                        txtId.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_eliminar.this, "Error de conexión: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(stringRequest);
    }
}