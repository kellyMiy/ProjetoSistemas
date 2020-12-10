package com.example.projetosistemas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class CadUsuario extends AppCompatActivity {
    Toolbar toolbar;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void CadUsuario(View v) {
        final TextView email = findViewById(R.id.txtEmail);
        final TextView senha = findViewById(R.id.txtSenha);
        final TextView confirmarSenha = findViewById(R.id.txtConfirmarSenha);
        if (!senha.getText().toString().equals(confirmarSenha.getText().toString())) {
            exibirMensagemErro("Confirmação de senha inválida.");
            return;
        }

        final Usuario usuario = new Usuario(null, email.getText().toString(), senha.getText().toString());
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:3000/api/usuarios";

        // Request a string response from the provided URL.
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, usuario.toJSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id");
                            usuario.setId(id);
                            Intent jan = new Intent(CadUsuario.this, ListaLembrete.class);
                            jan.putExtra("usuarioLogado", usuario);
                            startActivity(jan);
                            finish();
                        } catch (JSONException ex) {
                            exibirMensagemErro("Erro inesperado na tratativa do cadastro.");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                exibirMensagemErro("Credenciais inválidas!");
            }
        });
        queue.add(jsonRequest);
    }

    private void exibirMensagemErro(String mensagem) {
        dialog = new AlertDialog.Builder(CadUsuario.this)
                .setTitle("Erro")
                .setMessage(mensagem)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.cancel();
                        LimparSenha();
                    }
                })
                .show();
    }

    public void LimparSenha() {
        ((EditText) findViewById(R.id.txtSenha)).setText("");
        ((EditText) findViewById(R.id.txtConfirmarSenha)).setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}