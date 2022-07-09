package com.example.inventarisbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBarangKeluar extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbcenter;
    Button btn_update;
    EditText id,jumlah,tanggal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_barang_keluar);
        dbcenter = new DataHelper(this);
        id = findViewById(R.id.editidkeluar);
        jumlah = findViewById(R.id.editjumlahkeluar);
        tanggal = findViewById(R.id.edittanggalkeluar);
        btn_update = findViewById(R.id.btnupdatekeluar);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * from tb_keluar where id = '" +
                getIntent().getStringExtra("id")+"'",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            id.setText(cursor.getString(0).toString());
            jumlah.setText(cursor.getString(1).toString());
            tanggal.setText(cursor.getString(2).toString());
        }
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbcenter.getWritableDatabase();
                db.execSQL("update tb_keluar set id ='" +
                        id.getText().toString()+ "',jumlah = '" +
                        jumlah.getText().toString()+"',tanggal = '" +
                        tanggal.getText().toString()+"'where id = '"+
                        getIntent().getStringExtra("id")+"'");
                Toast.makeText(UpdateBarangKeluar.this, "Data Barang Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivityKeluar.makeluar.RefreshList();
                finish();
            }
        });
    }
}