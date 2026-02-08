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


public class activity_crear extends AppCompatActivity {


    EditText txtNombre, txtApellido, txtTelefono;
    Button btnCrear, btnRegistrar;

    String BASE_URL = "http://172.26.162.192/serv/index.php";
    String MY_KEY = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtTelefono = findViewById(R.id.txtTelefono);
        btnCrear = findViewById(R.id.btnCrear);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarContacto();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void guardarContacto() {
        // Obtener textos y quitar espacios
        String nom = txtNombre.getText().toString().trim();
        String app = txtApellido.getText().toString().trim();
        String tel = txtTelefono.getText().toString().trim();

        if (nom.isEmpty() || app.isEmpty() || tel.isEmpty()) {
            Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = BASE_URL + "?tipo=2&nom=" + nom + "&app=" + app + "&tel=" + tel + "&llave=" + MY_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(activity_crear.this, "Guardado con éxito", Toast.LENGTH_SHORT).show();

                        // Limpiar cajas
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtTelefono.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Si falla:
                        Toast.makeText(activity_crear.this, "Error de conexión: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(stringRequest);
    }
}