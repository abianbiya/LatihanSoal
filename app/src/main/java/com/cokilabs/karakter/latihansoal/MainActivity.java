package com.cokilabs.karakter.latihansoal;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SessionManager sessionManager = new SessionManager(this);

        FileDatabase fileDatabase = new FileDatabase(this);
        fileDatabase.writeToFile("json");

        String s = fileDatabase.readFromFile(this);

        ImageButton btn = findViewById(R.id.test1);
        ImageButton btn2 = findViewById(R.id.test2);
        ImageButton info = findViewById(R.id.info);
        ImageButton exit = findViewById(R.id.exit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View view1 = getLayoutInflater().inflate(R.layout.identitas, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setView(view1);
                dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText nama = view1.findViewById(R.id.nama);
                        EditText usia = view1.findViewById(R.id.usia);
                        String namastr = nama.getText().toString();
                        String usiastr = usia.getText().toString();
                        User user = new User();
                        user.setNama(namastr);
                        user.setUsia(usiastr);
                        sessionManager.saveLogedInProfile(user);

                        Intent intent = new Intent(MainActivity.this, SoalActivity.class);
                        intent.putExtra("paket", "paket_2");
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View view1 = getLayoutInflater().inflate(R.layout.identitas, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setView(view1);
                dialog.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText nama = view1.findViewById(R.id.nama);
                        EditText usia = view1.findViewById(R.id.usia);
                        String namastr = nama.getText().toString();
                        String usiastr = usia.getText().toString();
                        User user = new User();
                        user.setNama(namastr);
                        user.setUsia(usiastr);
                        sessionManager.saveLogedInProfile(user);

                        Intent intent = new Intent(MainActivity.this, SoalActivity.class);
                        intent.putExtra("paket", "paket_2");
                        startActivity(intent);
                    }
                });
                dialog.show();

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("Apakah Anda yakin ingin keluar?");
                dialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        System.exit(0);
                    }
                });
                dialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                String petunjuk = loadJSONFromAsset(MainActivity.this, "about.txt");
                dialog.setMessage(Html.fromHtml(petunjuk));
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
            }
        });


    }

    public String loadJSONFromAsset(Context context, String file) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(file);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
