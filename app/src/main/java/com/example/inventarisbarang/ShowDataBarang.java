package com.example.inventarisbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowDataBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbcenter;
    TextView id,nama,jenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_barang);
        dbcenter = new DataHelper(this);
        id = findViewById(R.id.id);
        nama = findViewById(R.id.nama);
        jenis = findViewById(R.id.jenis);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * from tb_barang where nama = '" +
                getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            jenis.setText(cursor.getString(2).toString());
        }
    }
}