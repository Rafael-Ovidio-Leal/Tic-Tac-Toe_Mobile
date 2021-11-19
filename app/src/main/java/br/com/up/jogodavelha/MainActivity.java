package br.com.up.jogodavelha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {

    private FloatingActionButton fabAddLink;
    private TextInputLayout textInputLayout, textInputLayout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddLink = findViewById(R.id.fab_main_go);
        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout2 = findViewById(R.id.textInputLayout2);

        fabAddLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNome();
            }
        });
    }

    private void callGameActivity(String nomeX, String nomeO){

        Intent intent = new Intent(MainActivity.this,
                GameActivity.class);
        intent.putExtra("O",nomeO);
        intent.putExtra("X",nomeX);
        startActivityForResult(intent,1000);
    }

    private void saveNome(){

        String nomeX = textInputLayout2.getEditText().getEditableText().toString();
        String nomeO = textInputLayout.getEditText().getEditableText().toString();

        if(nomeX.isEmpty()){
            textInputLayout2.setError("Por favor coloque o nome do jogador X");
        }
        if (nomeO.isEmpty()){
            textInputLayout.setError("Por favor coloque o nome do jogador O");
        }
        if(!nomeO.isEmpty() && !nomeX.isEmpty()){

            callGameActivity(nomeX, nomeO);
        }
    }

}