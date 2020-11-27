package com.example.projetosistemas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LembreteAdapter extends ArrayAdapter<Lembrete> {
    private final Context context;
    private final ArrayList<Lembrete> elementos;

    public LembreteAdapter(Context context, ArrayList<Lembrete> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);
        TextView titulo = (TextView)rowView.findViewById(R.id.titulo);
        TextView descricao = (TextView)rowView.findViewById(R.id.descricao);
        TextView dataConclusao = (TextView)rowView.findViewById(R.id.dataConclusao);
        titulo.setText(elementos.get(position).getTitulo());
        descricao.setText(elementos.get(position).getDescricao());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", new Locale("pt","BR"));
        dataConclusao.setText(sdf.format(elementos.get(position).getDataConclusao()));
        rowView.setTag(elementos.get(position).getId());
        return rowView;
    }
}
