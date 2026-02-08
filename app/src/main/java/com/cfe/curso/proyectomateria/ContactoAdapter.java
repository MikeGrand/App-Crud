package com.cfe.curso.proyectomateria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ViewHolder> {

    JSONArray listaDatos;

    public ContactoAdapter(JSONArray listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            // Obtenemos el objeto JSON de la posición actual
            JSONObject contacto = listaDatos.getJSONObject(position);

            // AQUÍ ES DONDE ASIGNAMOS LOS DATOS EXACTAMENTE COMO EN TU IMAGEN
            // Usamos las claves "id", "nom", "app", "tel" que vienen de tu PHP
            holder.tvId.setText("id: " + contacto.getString("id"));
            holder.tvNombre.setText("Nombre: " + contacto.getString("nom"));
            holder.tvApellido.setText("Apellido: " + contacto.getString("app"));
            holder.tvTelefono.setText("Telefono: " + contacto.getString("tel"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listaDatos.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declaramos los 4 Textviews
        TextView tvId, tvNombre, tvApellido, tvTelefono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Los vinculamos con el XML item_contacto
            tvId = itemView.findViewById(R.id.tvId);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellido = itemView.findViewById(R.id.tvApellido);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
        }
    }
}