package com.example.inventarisbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbcenter;
    Button btn_buat;
    EditText id,nama,jenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_barang);
        dbcenter = new DataHelper(this);
        id = findViewById(R.id.editid);
        nama = findViewById(R.id.editnama);
        jenis = findViewById(R.id.editjenis);
        btn_buat = findViewById(R.id.btnbuat);
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
        btn_buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbcenter.getWritableDatabase();
                db.execSQL("update tb_barang set nama='" +
                        nama.getText().toString()+ "',jenis = '" +
                        jenis.getText().toString()+"'where nama = '"+
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(UpdateDataBarang.this, "Data Barang Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivityBarang.mabarang.RefreshList();
                finish();
            }
        });
    }
}