package com.example.inventarisbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowBarangMasuk extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbcenter;
    TextView id,jumlah,tanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_barang_masuk);
        dbcenter = new DataHelper(this);
        id = findViewById(R.id.idmasuk);
        jumlah = findViewById(R.id.jumlahmasuk);
        tanggal = findViewById(R.id.tanggalmasuk);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * from tb_masuk where id = '" +
                getIntent().getStringExtra("id")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0).toString());
            jumlah.setText(cursor.getString(1).toString());
            tanggal.setText(cursor.getString(2).toString());
        }
    }
}