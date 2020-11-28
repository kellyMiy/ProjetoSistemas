package com.example.projetosistemas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String id;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public static Usuario fromJson(JSONObject jsonObject) {
        Usuario u = new Usuario();
        // Deserialize json into object fields
        try {
            u.id = jsonObject.getString("_id");
            u.email = jsonObject.getString("email");
            u.senha = jsonObject.getString("senha");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return u;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", getId());
            jsonObject.put("email", getEmail());
            jsonObject.put("senha", getSenha());

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
