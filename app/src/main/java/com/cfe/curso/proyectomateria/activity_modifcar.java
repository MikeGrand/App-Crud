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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class activity_modifcar extends AppCompatActivity {

    EditText txtId, txtNombre, txtApellido, txtTelefono;
    Button btnModifcar, btnRegistrar;

    String BASE_URL = "http://172.26.162.192/serv/index.php";
    String MY_KEY = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modifcar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtId = findViewById(R.id.txtId);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtTelefono = findViewById(R.id.txtTelefono);

        btnModifcar = findViewById(R.id.btnModifcar);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnModifcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarYModificar();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void verificarYModificar() {
        String id = txtId.getText().toString().trim();
        String nom = txtNombre.getText().toString().trim();
        String app = txtApellido.getText().toString().trim();
        String tel = txtTelefono.getText().toString().trim();

        if (id.isEmpty()) {
            Toast.makeText(this, "El ID es obligatorio para modificar", Toast.LENGTH_SHORT).show();
            return;
        }
        if (nom.isEmpty() || app.isEmpty() || tel.isEmpty()) {
            Toast.makeText(this, "Llena los nuevos datos", Toast.LENGTH_SHORT).show();
            return;
        }

        String urlConsulta = BASE_URL + "?tipo=1&llave=" + MY_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest requestConsulta = new StringRequest(Request.Method.GET, urlConsulta,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject root = new JSONObject(response);
                            boolean idEncontrado = false;

                            if (root.has("dato")) {
                                JSONArray listaArray = root.getJSONArray("dato");
                                for (int i = 0; i < listaArray.length(); i++) {
                                    JSONObject contacto = listaArray.getJSONObject(i);
                                    if (contacto.getString("id").equals(id)) {
                                        idEncontrado = true;
                                        break;
                                    }
                                }
                            }

                            if (idEncontrado) {
                                enviarSolicitudModificacion(id, nom, app, tel);
                            } else {
                                Toast.makeText(activity_modifcar.this, "El ID ingresado NO existe", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(activity_modifcar.this, "Error al verificar ID", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_modifcar.this, "Error de conexión al verificar", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(requestConsulta);
    }

    private void enviarSolicitudModificacion(String id, String nom, String app, String tel) {
        String urlUpdate = BASE_URL + "?tipo=3&id=" + id + "&nom=" + nom + "&app=" + app + "&tel=" + tel + "&llave=" + MY_KEY;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlUpdate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(activity_modifcar.this, "Modificado correctamente", Toast.LENGTH_SHORT).show();
                        txtId.setText("");
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtTelefono.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity_modifcar.this, "Error de conexión: " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(stringRequest);
    }
}