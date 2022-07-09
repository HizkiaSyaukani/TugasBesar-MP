package com.example.inventarisbarang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnbarang;
    Button btnmasuk;
    Button btnkeluar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnbarang = (Button) findViewById(R.id.buttonbarang);
        btnmasuk = (Button) findViewById(R.id.buttonmasuk);
        btnkeluar = (Button) findViewById(R.id.buttonkeluar);

        btnbarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDataBarang();
            }
        });

        btnmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDataMasuk();
            }
        });

        btnkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDataKeluar();
            }
        });
    }
    public void openDataBarang(){
        Intent ibrg = new Intent(this,MainActivityBarang.class);
        startActivity(ibrg);
    }
    public void openDataMasuk(){
        Intent imsk = new Intent(this,MainActivityMasuk.class);
        startActivity(imsk);
    }
    public void openDataKeluar(){
        Intent iklr = new Intent(this,MainActivityKeluar.class);
        startActivity(iklr);
    }
}