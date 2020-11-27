package com.example.projetosistemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void BotaoLogin(View v) {

        Intent jan = new Intent(MainActivity.this, ListaLembrete.class);
        startActivity(jan);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}