package com.example.inventarisbarang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Inventaris.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sqlbarang = "create table tb_barang(id text null,nama text null,jenis text null);";
        Log.d("Data","onCreate "+sqlbarang);
        db.execSQL(sqlbarang);
        String sqlmasuk = "create table tb_masuk(id text null,jumlah integer null,tanggal text null);";
        Log.d("Data","onCreate "+sqlmasuk);
        db.execSQL(sqlmasuk);
        String sqlkeluar = "create table tb_keluar(id text null,jumlah integer null,tanggal text null);";
        Log.d("Data","onCreate "+sqlkeluar);
        db.execSQL(sqlkeluar);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
