package com.omerfpekgoz.uygulama_bayrakquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BayraklarDao {

    public ArrayList<Bayraklar>  rastgele5Getir (DBHelper dbHelper){   //Burada bize rastgele 5 tane bayrak getirecek

        ArrayList<Bayraklar> bayraklarArrayList=new ArrayList<>();

        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null);

        while (c.moveToNext()){
            Bayraklar bayrak=new Bayraklar(c.getInt(c.getColumnIndex("bayrak_id"))
                    ,c.getString(c.getColumnIndex("bayrak_ad"))
                    ,c.getString(c.getColumnIndex("bayrak_resim")));

            bayraklarArrayList.add(bayrak);
        }

        return bayraklarArrayList;
    }

    //Burada bize doğru şık haricinde 3 tane şık getirecek.bayrak_id doğru şıkkın id si olacak
    public ArrayList<Bayraklar> rastegele3YanlisGetir(DBHelper dbHelper,int bayrak_id ){
        ArrayList<Bayraklar> bayraklarArrayList=new ArrayList<>();
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id!="+bayrak_id+" ORDER BY RANDOM() LIMIT 3",null);

        while (c.moveToNext()){

            Bayraklar bayrak=new Bayraklar(c.getInt(c.getColumnIndex("bayrak_id"))
            ,c.getString(c.getColumnIndex("bayrak_ad"))
            ,c.getString(c.getColumnIndex("bayrak_resim")));

            bayraklarArrayList.add(bayrak);
        }

        return bayraklarArrayList;
    }
}
