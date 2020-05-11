package com.omerfpekgoz.uygulama_bayrakquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView txtSonuc,txtBasariOrani;
    private Button btnTekrarDene;

    private int dogruSayac;
    private int yanlisSayac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtBasariOrani=findViewById(R.id.txtBasariOrani);
        txtSonuc=findViewById(R.id.txtSonuc);
        btnTekrarDene=findViewById(R.id.btnTekrarDene);

        dogruSayac=getIntent().getIntExtra("dogruSayac",0);
        yanlisSayac=getIntent().getIntExtra("yanlisSayac",0);

        txtSonuc.setText(dogruSayac+" DOĞRU  "+yanlisSayac+" YANLIŞ");
        txtBasariOrani.setText("% "+(dogruSayac*100)/5+" BAŞARI");

        btnTekrarDene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this,QuizActivity.class));
                finish();

            }
        });
    }
}
