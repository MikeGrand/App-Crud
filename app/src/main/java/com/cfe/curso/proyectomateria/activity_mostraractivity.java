package com.cfe.curso.proyectomateria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class activity_mostraractivity extends AppCompatActivity {

    RecyclerView recyclerContactos;
    Button btnRegresar;

    // TU IP REAL Y TU LLAVE
    String BASE_URL = "http://172.26.162.192/serv/index.php";
    String MY_KEY = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostraractivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerContactos = findViewById(R.id.recyclerContactos);
        btnRegresar = findViewById(R.id.btnRegresar);

        // Configurar el RecyclerView para que sea una lista vertical
        recyclerContactos.setLayoutManager(new LinearLayoutManager(this));

        // Cargar los datos
        cargarDatos();

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Cierra la actividad y regresa
            }
        });
    }

    private void cargarDatos() {
        String url = BASE_URL + "?tipo=1&llave=" + MY_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // 1. Convertir la respuesta texto a JSON
                            JSONObject root = new JSONObject(response);

                            // 2. Obtener el array "lista" (así lo llamamos en PHP)
                            if (root.has("dato")) {
                                JSONArray listaArray = root.getJSONArray("dato");

                                // 3. Crear el adaptador y pasarselo al Recycler
                                ContactoAdapter adapter = new ContactoAdapter(listaArray);
                                recyclerContactos.setAdapter(adapter);
                            } else {
                                Toast.makeText(activity_mostraractivity.this, "No hay datos", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(activity_mostraractivity.this, "Error procesando datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_mostraractivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(stringRequest);
    }
}