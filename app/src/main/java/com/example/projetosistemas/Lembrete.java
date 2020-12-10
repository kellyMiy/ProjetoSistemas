package com.example.projetosistemas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lembrete implements Serializable {
    private String id;
    private String titulo;
    private String descricao;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private Date dataConclusao;
    private Date dataCadastro;
    private Usuario usuario;

    public Lembrete() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Lembrete(String id, String titulo, String descricao, Date dataCadastro, Date dataConclusao, Usuario usuario) {
        this.setId(id);
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setDataCadastro(dataCadastro);
        this.setDataConclusao(dataConclusao);
        this.setUsuario(usuario);
    }

    public static Lembrete fromJson(JSONObject jsonObject) {
        Lembrete l = new Lembrete();

        try {
            l.id = jsonObject.getString("_id");
            l.titulo = jsonObject.getString("titulo");
            l.descricao = jsonObject.getString("descricao");
            l.dataCadastro = sdf.parse(jsonObject.getString("dataCadastro"));
            l.dataConclusao = sdf.parse(jsonObject.getString("dataConclusao"));
            l.usuario = new Usuario(jsonObject.getString("idUsuario"), null, null);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
            return null;
        }
        return l;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", getId());
            jsonObject.put("titulo", getTitulo());
            jsonObject.put("descricao", getDescricao());
            jsonObject.put("dataCadastro", getDataCadastro());
            jsonObject.put("dataConclusao", getDataConclusao());
            jsonObject.put("idUsuario", getUsuario().getId());

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

