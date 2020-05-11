package com.omerfpekgoz.uygulama_bayrakquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnBasla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasla=findViewById(R.id.btnBasla);
        veritabaniKopyalama();

        btnBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               startActivity(new Intent(MainActivity.this,QuizActivity.class));


            }
        });
    }
    public void veritabaniKopyalama(){
        DatabaseCopyHelper databaseCopyHelper=new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        databaseCopyHelper.openDataBase();
    }
}
