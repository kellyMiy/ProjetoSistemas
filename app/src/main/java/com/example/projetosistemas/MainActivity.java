package com.example.projetosistemas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BotaoLogin(View v) {
        final TextView email = findViewById(R.id.txtEmail);
        final TextView senha = findViewById(R.id.txtSenha);
        final Usuario usuario = new Usuario(null, email.getText().toString(), senha.getText().toString());
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000/api/login";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, usuario.toJSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Usuario usuarioLogado = Usuario.fromJson(response);
                        Intent jan = new Intent(MainActivity.this, ListaLembrete.class);
                        jan.putExtra("usuarioLogado", usuarioLogado);
                        startActivity(jan);
                        //finish();
                    }
                }, new Response.ErrorListener() {
            private AlertDialog dialog;

            @Override
            public void onErrorResponse(VolleyError error) {
                dialog = new AlertDialog.Builder(MainActivity.this) // Pass a reference to your main activity here
                        .setTitle("Erro")
                        .setMessage("Credenciais inválidas!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialog.cancel();
                                LimparSenha();
                            }
                        })
                        .show();
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonRequest);
    }

    public void LimparSenha() {
        ((EditText) findViewById(R.id.txtSenha)).setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}