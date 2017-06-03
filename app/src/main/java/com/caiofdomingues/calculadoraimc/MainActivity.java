package com.caiofdomingues.calculadoraimc;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAct";

    private View activityMain;
    private TextView lblResultado;
    private EditText txtPeso;
    private EditText txtAltura;
    private Button btnCalcular;//o botao é um objeto da Calse Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();//agora que ja temos os view, vamos dar ao botao uma açao (action)
        setActions();
    }

    private void findViews(){
        activityMain = findViewById(R.id.activity_main);
        lblResultado = (TextView)findViewById(R.id.lblResultado);
        txtPeso = (EditText) findViewById(R.id.txtPeso);
        txtAltura = (EditText) findViewById(R.id.txtAltura);
        btnCalcular = (Button) findViewById(R.id.botaocalimc);
    }

    private void setActions(){
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pesoString = txtPeso.getText().toString();
                String alturaString = txtAltura.getText().toString();
                if(!pesoString.isEmpty() && !alturaString.isEmpty()){
                    int peso = Integer.parseInt(pesoString);
                    int altura = Integer.parseInt(alturaString);
                    btnCalcularOnClick(peso, altura);
                }else{
                    showSnack();
                }

            }
        });

    }

    public void btnCalcularOnClick(int peso, int altura){
        double resultado = peso / ( (altura*0.01) * (altura * 0.01));

        if(resultado < 19){
            //abaixo
            lblResultado.setText(getString(R.string.s_menor_que_19));
        }
        else if(resultado > 32){
            //obeso
            lblResultado.setText(getString(R.string.s_maior_que_32));
        }
        else{
            //ok
            lblResultado.setText(getString(R.string.s_peso_ok));
        }
    }

    private void showSnack(){
        Snackbar.make(activityMain, getString(R.string.s_sem_dados), Snackbar.LENGTH_SHORT)
                .setAction("", null)
                .show();
    }
}
