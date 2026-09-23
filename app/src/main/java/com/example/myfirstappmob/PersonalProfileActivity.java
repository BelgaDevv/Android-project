package com.example.myfirstappmob;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PersonalProfileActivity extends AppCompatActivity {

    //Instância do sharedPreferences
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /*
        criação e acesso do arquivo de preferencias nomeado como
        "UserProfile". -- MODE PRIVATE define que outro aplicativo
        não pode acessar esse arquivo
         */
        sharedPreferences = getSharedPreferences(
                "UserProfile",
                MODE_PRIVATE
        );

        /*
        Procura na tela um componente através do seu id e tipo
         */
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextPhone = findViewById(R.id.editTextPhone);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        CheckBox checkBox1 = findViewById(R.id.CheckBoxProfile1);
        CheckBox checkBox2 = findViewById(R.id.CheckBoxProfile2);
        CheckBox checkBox3 = findViewById(R.id.CheckBoxProfile3);


        /*
        Recupera os valores armazenados no SharedPreferences
        e coloca cada um em uma variável e define valores default.
        */
        String name = sharedPreferences.getString("name", "admin");
        String email = sharedPreferences.getString("email", "");
        String phone = sharedPreferences.getString("phone", "");
        int gender = sharedPreferences.getInt("gender", R.id.radioMan);
        boolean check1 = sharedPreferences.getBoolean("check1", false);
        boolean check2 = sharedPreferences.getBoolean("check2", false);
        boolean check3 = sharedPreferences.getBoolean("check3", false);

        /*
        Coloca os valores recuperados do SharedPreferences
        novamente nos campos da tela.
         */
        editTextName.setText(name);
        editTextEmail.setText(email);
        editTextPhone.setText(phone);
        checkBox1.setChecked(check1);
        checkBox2.setChecked(check2);
        checkBox3.setChecked(check3);

        /*
        Verifica se existe um gênero salvo no SharedPreferences.
        Caso exista, seleciona o RadioButton correspondente ao ID salvo.
         */
        if (gender != -1) {
            rgGender.check(gender);
        }

        // instancia do botão salvar
        Button btnSalvar = findViewById(R.id.btnSalvar);

        /*
         Define um listener que será executado quando
         o botão "Salvar" for clicado.
         */
        btnSalvar.setOnClickListener(v -> {

             /*
             Cria um Editor para permitir alterações
             nos dados armazenados no SharedPreferences.
            */
            SharedPreferences.Editor editor = sharedPreferences.edit();

             /*
            Pega o texto atual de cada campo da tela
            e converte para String.
            */
            String newName = editTextName.getText().toString();
            String newEmail = editTextEmail.getText().toString();
            String newPhone = editTextPhone.getText().toString();
            int selectedId = rgGender.getCheckedRadioButtonId();

            /*
            Armazena os novos valores no Editor,
            associando cada valor à sua respectiva chave.
            */
            editor.putString("nome", newName);
            editor.putString("email", newEmail);
            editor.putString("telefone", newPhone);
            editor.putInt("gender", selectedId);
            editor.putBoolean("check1", checkBox1.isChecked());
            editor.putBoolean("check2", checkBox2.isChecked());
            editor.putBoolean("check3", checkBox3.isChecked());

            //Aplica as alterações e salva os dados no SharedPreferences.
            editor.apply();

            Toast.makeText(this, "dados salvos", Toast.LENGTH_SHORT).show();

            finish();
        });
/*
instancia button CancelarPofile
 */
        Button btnCancelarProfile = findViewById(R.id.btnCancelarProfile);

/*
listener do botão cancelar que finaliza aquele processo
 */
        btnCancelarProfile.setOnClickListener(v -> {
            finish();
        });
    }
}