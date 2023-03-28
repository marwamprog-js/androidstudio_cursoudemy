package com.cursoandroid.cursoandroidudemy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private EditText editNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.buttonSalvar);
        editNome = findViewById(R.id.editNome);
        textResultado = findViewById(R.id.textResultado);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                //Validar o nome
                if( editNome.getText().toString().equals("") ) {
                    Toast.makeText( getApplicationContext(), "Preencha o nome.", Toast.LENGTH_LONG ).show();
                } else {

                    String nome = editNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();

                    textResultado.setText("Olá, " + nome);

                }

            }
        });

        //Recuperar dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //Validar se temos o nome em preferencias
        if( preferences.contains("nome") ) {

            String nome = preferences.getString("nome", "usuário não definido");
            textResultado.setText("Olá, " + nome);

        } else {
            textResultado.setText("Olá, usuário não definido");
        }

    }

}