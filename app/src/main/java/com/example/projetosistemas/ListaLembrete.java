package com.example.projetosistemas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class ListaLembrete extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lembrete);

        ListView lista = (ListView) findViewById(R.id.listView);
        ArrayList<Lembrete> lembretes = adicionarLembretes();
        ArrayAdapter adapter = new LembreteAdapter(this, lembretes);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

            private AlertDialog dialog;
        });
    }

    public void AdicionarLembrete(View v) {
        Intent jan = new Intent(ListaLembrete.this, CadLembrete.class);
        startActivity(jan);
    }

    private ArrayList<Lembrete> adicionarLembretes() {
        ArrayList<Lembrete> lembretes = new ArrayList<Lembrete>();
        Lembrete e = new Lembrete("12309", "Colégio Estadual Atheneu Sergipense", "Rua Pacatuba S/N", new Date());
        lembretes.add(e);
        e = new Lembrete("90831092", "Escola Estadual General Siqueira", "Rua SergipeS/N", new Date());
        lembretes.add(e);
        e = new Lembrete("0909090942", "Escola Tobias Barreto", "Av.OtonielDórea", new Date());
        lembretes.add(e);
        return lembretes;
    }

}