package com.example.inventarisbarang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivityMasuk extends AppCompatActivity {
    String[] daftar;
    ListView listView;
    Menu menu;
    DataHelper dbcenter;
    protected Cursor cursor;
    public static MainActivityMasuk mamasuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_masuk);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ibuat = new Intent(MainActivityMasuk.this,CreateBarangMasuk.class);
                startActivity(ibuat);
            }
        });
        mamasuk = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tb_masuk ",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int i = 0;i < cursor.getCount();i++){
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0).toString();
        }
        listView = (ListView) findViewById(R.id.ListView);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Detail Barang","Edit Barang","Hapus Barang"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityMasuk.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item){
                            case 0:
                                Intent ilihat = new Intent(getApplicationContext(),ShowBarangMasuk.class);
                                ilihat.putExtra("id",selection);
                                startActivity(ilihat);
                                break;
                            case 1:
                                Intent iupdate = new Intent(getApplicationContext(),UpdateBarangMasuk.class);
                                iupdate.putExtra("id",selection);
                                startActivity(iupdate);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from tb_masuk where id='"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
}