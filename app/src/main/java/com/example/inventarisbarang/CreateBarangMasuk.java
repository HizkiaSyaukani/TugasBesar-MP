package com.example.inventarisbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateBarangMasuk extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbcenter;
    Button btn_buat;
    EditText id,jumlah,tanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_barang_masuk);
        dbcenter = new DataHelper(this);
        id = findViewById(R.id.editidmasuk);
        jumlah = findViewById(R.id.editjumlahmasuk);
        tanggal = findViewById(R.id.edittanggalmasuk);
        btn_buat = findViewById(R.id.btnbuatmasuk);
        btn_buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbcenter.getWritableDatabase();
                db.execSQL("insert into tb_masuk(id,jumlah,tanggal) values('" +
                        id.getText().toString() +"','" +
                        jumlah.getText().toString() +"','" +
                        tanggal.getText().toString() + "')");
                Toast.makeText(CreateBarangMasuk.this, "Data Barang Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivityMasuk.mamasuk.RefreshList();
                finish();
            }
        });

    }
}