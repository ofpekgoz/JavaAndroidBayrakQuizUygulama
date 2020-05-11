
package com.omerfpekgoz.uygulama_bayrakquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView txtDogruSayi,txtYanlisSayi,txtSoruSayisi;
    private ImageView imgBayrak;
    private Button btnA,btnB,btnC,btnD;

    private ArrayList<Bayraklar> sorularListesi;
    private ArrayList<Bayraklar> yanlisSecenekListesi;
    private Bayraklar dogruSoru;
    private DBHelper dbHelper;

    //Sayaclar
    private int soruSayac=0;
    private int yanlisSayac=0;
    private int dogruSayac=0;

    //Seçenekler Karıştırma
    private HashSet<Bayraklar> seceneklerKaristirmaListesi=new HashSet<>();  //Rastgele seçenek ataması için HashSet kullandık
    private ArrayList<Bayraklar> seceneklerListesi=new ArrayList<>();  //HashSet den gelen secenekleri ArrayList e atacağız





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        txtDogruSayi=findViewById(R.id.txtDogruSayi);
        txtYanlisSayi=findViewById(R.id.txtYanlisSayi);
        txtSoruSayisi=findViewById(R.id.txtSoruSayisi);
        btnA=findViewById(R.id.btnA);
        btnB=findViewById(R.id.btnB);
        btnC=findViewById(R.id.btnC);
        btnD=findViewById(R.id.btnD);
        imgBayrak=findViewById(R.id.imgBayrak);


        dbHelper=new DBHelper(this);
        sorularListesi=new BayraklarDao().rastgele5Getir(dbHelper);  //İlk başta rastgele soru gelecek
        soruYukle();





        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soruDogruMu(btnA);
                sayacKontrol();

            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soruDogruMu(btnB);
                sayacKontrol();

            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soruDogruMu(btnC);
                sayacKontrol();

            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soruDogruMu(btnD);
                sayacKontrol();

            }
        });
    }

    public void soruYukle() {



        txtSoruSayisi.setText(soruSayac+1+".SORU");  //Soru sayısını yazdırdık
        txtDogruSayi.setText("Doğru : "+dogruSayac);
        txtYanlisSayi.setText("Yanlış: "+yanlisSayac);



        dogruSoru=sorularListesi.get(soruSayac);
        yanlisSecenekListesi=new BayraklarDao().rastegele3YanlisGetir(dbHelper,dogruSoru.getBayrak_id());
        imgBayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(),"drawable",getPackageName()));//Rastgele bayrak getireceği için R.drawable diye tanımlama yapmadık

        seceneklerKaristirmaListesi.clear(); //Seçenek karıştırma listesini temizledik yani sıfırladık
        seceneklerKaristirmaListesi.add(dogruSoru);
        seceneklerKaristirmaListesi.add(yanlisSecenekListesi.get(0));
        seceneklerKaristirmaListesi.add(yanlisSecenekListesi.get(1));
        seceneklerKaristirmaListesi.add(yanlisSecenekListesi.get(2));

        seceneklerListesi.clear();

        for (Bayraklar b:seceneklerKaristirmaListesi){
            seceneklerListesi.add(b);
        }

        btnA.setText(seceneklerListesi.get(0).getBayrak_ad());
        btnB.setText(seceneklerListesi.get(1).getBayrak_ad());
        btnC.setText(seceneklerListesi.get(2).getBayrak_ad());
        btnD.setText(seceneklerListesi.get(3).getBayrak_ad());

    }

    public void soruDogruMu(Button button){

        String butonYazi=button.getText().toString();
        String dogruCevap=dogruSoru.getBayrak_ad();

        if (butonYazi.equals(dogruCevap)){

            dogruSayac++;
           // button.setBackgroundResource(R.color.colorDogru);



        }
        else{
            yanlisSayac++;
           // button.setBackgroundResource(R.color.colorYanlis);
        }

    }

    public void sayacKontrol(){

        soruSayac++;
        if (soruSayac!=5){

            soruYukle();
        }
        else{
            Intent intent=new Intent(QuizActivity.this,ResultActivity.class);
            intent.putExtra("dogruSayac",dogruSayac);
            intent.putExtra("yanlisSayac",yanlisSayac);
            startActivity(intent);
            finish();

        }

    }
}
