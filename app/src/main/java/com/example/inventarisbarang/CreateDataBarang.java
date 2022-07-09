package com.example.inventarisbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateDataBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbcenter;
    Button btn_buat;
    EditText id,nama,jenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data_barang);
        dbcenter = new DataHelper(this);
        id = findViewById(R.id.editid);
        nama = findViewById(R.id.editnama);
        jenis = findViewById(R.id.editjenis);
        btn_buat = findViewById(R.id.btnbuat);
        btn_buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbcenter.getWritableDatabase();
                db.execSQL("insert into tb_barang(id,nama,jenis) values('" +
                        id.getText().toString() +"','" +
                        nama.getText().toString() +"','" +
                        jenis.getText().toString() + "')");
                Toast.makeText(CreateDataBarang.this, "Data Barang Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivityBarang.mabarang.RefreshList();
                finish();
            }
        });

    }
}