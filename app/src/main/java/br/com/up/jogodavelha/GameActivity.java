package br.com.up.jogodavelha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;

import br.com.up.jogodavelha.R;


public class GameActivity extends AppCompatActivity {

    private Button[] arrayButton = new Button[10];
    private String vez = "X";
    private int turnos = 0;
    private String[] matriz = new String[10];
    private String[] array = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle("Jogo da Velha");
        inicializaButtons();
        onClickButtons();
    }

    private void inicializaButtons() {
        arrayButton[1] = (Button) findViewById(R.id.btn1);
        arrayButton[2] = (Button) findViewById(R.id.btn2);
        arrayButton[3] = (Button) findViewById(R.id.btn3);
        arrayButton[4] = (Button) findViewById(R.id.btn4);
        arrayButton[5] = (Button) findViewById(R.id.btn5);
        arrayButton[6] = (Button) findViewById(R.id.btn6);
        arrayButton[7] = (Button) findViewById(R.id.btn7);
        arrayButton[8] = (Button) findViewById(R.id.btn8);
        arrayButton[9] = (Button) findViewById(R.id.btn9);
    }

    private void onClickButtons() {
        for (int x = 1; x < 10; x++) {
            int finalX = x;
            arrayButton[finalX].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jogada(finalX);
                }
            });
            matriz[x] = "";
        }
    }

    private void jogada(int x) {
        if (matriz[x] == "") {
            matriz[x] = vez;
            turnos++;
            if (vez == "X") {
                vez = "O";
            } else {
                vez = "X";
            }
        }
        exibirButtons();
        verificar();
    }

    private void exibirButtons() {
        for (int x = 1; x < 10; x++) {
            arrayButton[x].setText(matriz[x]);
        }
    }



    private void verificar() {
        if (matriz[1].equals(matriz[2]) && matriz[1].equals(matriz[3]) && matriz[1].toString() != "") {
            vencedor(matriz[1]);
            return;
        }

        if (matriz[4].equals(matriz[5]) && matriz[4].equals(matriz[6]) && matriz[4].toString() != "") {
            vencedor(matriz[4]);
            return;
        }

        if (matriz[7].equals(matriz[8]) && matriz[7].equals(matriz[9]) && matriz[7].toString() != "") {
            vencedor(matriz[7]);
            return;
        }

        if (matriz[1].equals(matriz[4]) && matriz[1].equals(matriz[7]) && matriz[1].toString() != "") {
            vencedor(matriz[1]);
            return;
        }

        if (matriz[2].equals(matriz[5]) && matriz[2].equals(matriz[8]) && matriz[2].toString() != "") {
            vencedor(matriz[2]);
            return;
        }

        if (matriz[3].equals(matriz[6]) && matriz[3].equals(matriz[9]) && matriz[3].toString() != "") {
            vencedor(matriz[3]);
            return;
        }

        if (matriz[1].equals(matriz[5]) && matriz[1].equals(matriz[9]) && matriz[1].toString() != "") {
            vencedor(matriz[1]);
            return;
        }

        if (matriz[7].equals(matriz[5]) && matriz[7].equals(matriz[3]) && matriz[7].toString() != "") {
            vencedor(matriz[7]);
            return;
        }

        if (turnos == 9) {
            vencedor("");
            return;
        }
    }

    private void vencedor(String vencedor) {
        Intent intent = getIntent();
        String nomeO = intent.getStringExtra("O");
        String nomeX = intent.getStringExtra("X");
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle("Resultado");
        if (vencedor.equals("")) {
            builder.setMessage("Empate");
        } else {
            if (vencedor.equals("X")) {
                builder.setMessage(nomeX + " é o vencedor");
            } else {
                builder.setMessage(nomeO + " é o vencedor");
            }
        }
        builder.setPositiveButton("Jogar Novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                turnos = 0;
                for (int x = 1; x < 10; x++) {
                    matriz[x] = "";
                }
                exibirButtons();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}