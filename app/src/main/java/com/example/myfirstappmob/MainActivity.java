package com.example.myfirstappmob;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // xml correspondente
        setContentView(R.layout.activity_main);

        // Exibe aviso de APP iniciado
        Toast.makeText(this, "App iniciado", Toast.LENGTH_SHORT).show();

        // Cuida da exibição da interface
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*
        cria um atributo relacionado ao elemento da view "btnsair"
        e define um listener que reage ao clique do botão encerrando a aplicação
        e criando um toast de aviso "APPfechado"
         */
        Button btnSair = findViewById(R.id.btnSair);
        btnSair.setOnClickListener(v -> {
            Toast.makeText(this, "APPfechado", Toast.LENGTH_SHORT).show();
        finishAffinity();
        });

        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(v -> {
            // Cria o Intent passando a tela atual (MainActivity) e a tela de destino (SegundaActivity)
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish(); // impede o usuário de voltar para a tela de login
        });
    }
}