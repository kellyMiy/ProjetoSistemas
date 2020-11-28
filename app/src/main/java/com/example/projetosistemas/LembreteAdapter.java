package com.example.projetosistemas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class LembreteAdapter extends ArrayAdapter<Lembrete> {
    private final Context context;
    private final ArrayList<Lembrete> elementos;
    private final LembreteAdapterCallback callback;

    public LembreteAdapter(Context context, ArrayList<Lembrete> elementos, LembreteAdapterCallback callback) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
        this.callback = callback;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);
        TextView titulo = (TextView) rowView.findViewById(R.id.lblTitulo);
        TextView descricao = (TextView) rowView.findViewById(R.id.lblDescricao);
        TextView dataConclusao = (TextView) rowView.findViewById(R.id.lblDataConclusao);
        TextView dataCadastro = (TextView) rowView.findViewById(R.id.lblDataCadastro);
        titulo.setText(elementos.get(position).getTitulo());
        descricao.setText(elementos.get(position).getDescricao());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", new Locale("pt", "BR"));
        dataConclusao.setText("previsto para " + sdf.format(elementos.get(position).getDataConclusao()));
        dataCadastro.setText("criado em " + sdf.format(elementos.get(position).getDataCadastro()));
        MaterialButton btnEditar = rowView.findViewById(R.id.btnEditar);
        btnEditar.setTag(elementos.get(position));
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lembrete lembrete = (Lembrete) v.getTag();
                callback.onEdit(lembrete);
            }
        });
        MaterialButton btnExcluir = rowView.findViewById(R.id.btnExcluir);
        btnExcluir.setTag(elementos.get(position));
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lembrete lembrete = (Lembrete) v.getTag();
                callback.onDelete(lembrete);
            }
        });

        return rowView;
    }

    public interface LembreteAdapterCallback {
        public void onEdit(Lembrete lembrete);

        public void onDelete(Lembrete lembrete);
    }
}
