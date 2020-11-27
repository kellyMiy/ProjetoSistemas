package com.example.projetosistemas;

import java.io.Serializable;
import java.util.Date;

public class Lembrete implements Serializable {
    private String id;
    private String titulo;
    private String descricao;
    private Date dataConclusao;

    private String endereco;

    public Lembrete(String id, String titulo, String descricao, Date dataConclusao) {
        this.setId(id);
        this.setTitulo(titulo);
        this.setDescricao(descricao);
        this.setDataConclusao(dataConclusao);
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

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
}

