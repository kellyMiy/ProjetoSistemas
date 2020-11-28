package com.example.projetosistemas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListaLembrete extends AppCompatActivity implements LembreteAdapter.LembreteAdapterCallback {

    ListView listView;
    Usuario usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lembrete);
        Intent intent = getIntent();
        this.usuarioLogado = (Usuario) intent.getSerializableExtra("usuarioLogado");

        buscarLembretes();
    }

    public void AdicionarLembrete(View v) {
        Intent jan = new Intent(ListaLembrete.this, CadLembrete.class);
        jan.putExtra("usuarioLogado", this.usuarioLogado);
        startActivity(jan);
        //finish();
    }

    private void buscarLembretes() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000/api/tarefas/" + this.usuarioLogado.getId();

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("tarefas");
                            ArrayList<Lembrete> lembretes = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject item = (JSONObject) jsonArray.get(i);
                                lembretes.add(Lembrete.fromJson(item));
                            }
                            ListView lista = (ListView) findViewById(R.id.listView);
                            TextView vazio = findViewById(R.id.lblSemLembrete);
                            lista.setEmptyView(vazio);
                            ArrayAdapter adapter = new LembreteAdapter(ListaLembrete.this, lembretes, ListaLembrete.this);
                            lista.setAdapter(adapter);
                            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                private AlertDialog dialog;

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    dialog = new AlertDialog.Builder(ListaLembrete.this) // Pass a reference to your main activity here
                                            .setTitle("Titulo")
                                            .setMessage("Item: " + view.getTag())
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialog.cancel();
                                                }
                                            })
                                            .show();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog = new AlertDialog.Builder(ListaLembrete.this) // Pass a reference to your main activity here
                        .setTitle("Erro")
                        .setMessage("Credenciais inválidas!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }

            private AlertDialog dialog;
        });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onEdit(Lembrete lembrete) {
        Intent jan = new Intent(ListaLembrete.this, CadLembrete.class);
        jan.putExtra("usuarioLogado", this.usuarioLogado);
        jan.putExtra("lembreteEdicao", lembrete);
        startActivity(jan);
    }

    @Override
    public void onDelete(Lembrete lembrete) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000/api/tarefas/" + lembrete.getId();

        // Request a string response from the provided URL.
        StringRequest jsonRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        buscarLembretes();
                    }
                }, new Response.ErrorListener() {
            private AlertDialog dialog;

            @Override
            public void onErrorResponse(VolleyError error) {
                dialog = new AlertDialog.Builder(ListaLembrete.this) // Pass a reference to your main activity here
                        .setTitle("Erro")
                        .setMessage("Erro ao excluir o lembrete!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }
}