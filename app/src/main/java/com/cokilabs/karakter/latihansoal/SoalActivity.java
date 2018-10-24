package com.cokilabs.karakter.latihansoal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.FontRes;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.LoginFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SoalActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView soal_atas;
    TextView soal_bawah;
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView no;

    Button aa;
    Button bb;
    Button cc;
    Button dd;
    Button done;

    Integer index = 0;
    Integer nilai = 0;

    FloatingActionButton next;
    FloatingActionButton prev;

    ArrayList<Soal> soals;
    ArrayList<Jawaban> jawabans;
    ArrayList<Integer> terjawabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        soals = new ArrayList<Soal>();
        jawabans = new ArrayList<Jawaban>();
        terjawabs = new ArrayList<>();

        initToolbar(Html.fromHtml("Soal Latihan"));

        soal_atas = findViewById(R.id.soal_atas);
        soal_bawah = findViewById(R.id.soal_bawah);
        a = findViewById(R.id.a_nya);
        b= findViewById(R.id.b_nya);
        c= findViewById(R.id.c_nya);
        d = findViewById(R.id.d_nya);
        no = findViewById(R.id.no);

        aa = findViewById(R.id.a_btn);
        bb = findViewById(R.id.b_btn);
        cc = findViewById(R.id.c_btn);
        dd = findViewById(R.id.d_btn);

        next = findViewById(R.id.next);
        prev = findViewById(R.id.prev);

        String paketstr = getIntent().getStringExtra("paket");


        no.setText(String.valueOf(index+1));
        String petunjuk = loadJSONFromAsset(SoalActivity.this, "petunjuk.txt");
        final AlertDialog.Builder dialog = new AlertDialog.Builder(SoalActivity.this);
        dialog.setMessage(Html.fromHtml(petunjuk));
        dialog.setPositiveButton("Ya, Saya mengerti", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialog.show();


        final Gson gson = new Gson();

        String soaljsons = loadJSONFromAsset(this, "db_soal.json");
        try {
            JSONObject soaljson = new JSONObject(soaljsons);
            JSONArray paket = soaljson.getJSONArray(paketstr);
            Log.e("no1", paket.getString(0));

            for(int i = 0; i<paket.length(); i++){
                JSONObject obj = paket.getJSONObject(i);
                Soal soal = gson.fromJson(obj.toString(), Soal.class);
                soals.add(soal);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        soal_atas.setText(Html.fromHtml(soals.get(0).getSoalAtas()));
        soal_bawah.setText(Html.fromHtml(soals.get(0).getSoalBawah()));
        a.setText(Html.fromHtml(soals.get(0).getA()));
        b.setText(Html.fromHtml(soals.get(0).getB()));
        c.setText(Html.fromHtml(soals.get(0).getC()));
        d.setText(Html.fromHtml(soals.get(0).getD()));

        aa.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                njawab("a", index);
                setWarnaJawaban("a");
            }
        });
        bb.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                njawab("b", index);
                setWarnaJawaban("b");
            }
        });
        cc.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                njawab("c", index);
                setWarnaJawaban("c");
            }
        });
        dd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                njawab("d", index);
                setWarnaJawaban("d");
                dd.setBackground(getResources().getDrawable(R.drawable.bg_rounded_orange));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(index < soals.size()-1){
                    index = index + 1;
                }

                String jaw = findJawaban(soals.get(index).getNo());
                no.setText(String.valueOf(index+1));
                soal_atas.setText(Html.fromHtml(soals.get(index).getSoalAtas()));
                soal_bawah.setText(Html.fromHtml(soals.get(index).getSoalBawah()));
                a.setText(Html.fromHtml(soals.get(index).getA()));
                b.setText(Html.fromHtml(soals.get(index).getB()));
                c.setText(Html.fromHtml(soals.get(index).getC()));
                d.setText(Html.fromHtml(soals.get(index).getD()));
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(index>0){
                    index = index - 1;
                }
                String jaw = findJawaban(soals.get(index).getNo());
                no.setText(String.valueOf(index+1));
                soal_atas.setText(Html.fromHtml(soals.get(index).getSoalAtas()));
                soal_bawah.setText(Html.fromHtml(soals.get(index).getSoalBawah()));
                a.setText(Html.fromHtml(soals.get(index).getA()));
                b.setText(Html.fromHtml(soals.get(index).getB()));
                c.setText(Html.fromHtml(soals.get(index).getC()));
                d.setText(Html.fromHtml(soals.get(index).getD()));
            }
        });

        String[] array = new String[25];
        for(Integer i=0; i<25; i++){
            Integer a = i + 1;
            array[i] = a.toString();
        }

        //===================--  GRIDDING  --=========================
        final View indexz = getLayoutInflater().inflate(R.layout.index_nomor, null);
        final GridView gridView = indexz.findViewById(R.id.grid);
        done = indexz.findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoalActivity.this, NilaiActivity.class);
                intent.putExtra("nilai", nilai);
                startActivity(intent);
                finish();
            }
        });
        final IndexAdapter adapter = new IndexAdapter(this, array, terjawabs);


        final AlertDialog dialogs = new AlertDialog.Builder(SoalActivity.this).create();
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(SoalActivity.this, nilai.toString(), Toast.LENGTH_SHORT).show();
                Log.e("nono", index.toString());
                adapter.notifyDataSetChanged();
                gridView.setAdapter(adapter);
                dialogs.setView(indexz);
                dialogs.show();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                findJawaban(soals.get(index).getNo());
                no.setText(String.valueOf(index+1));
                soal_atas.setText(Html.fromHtml(soals.get(index).getSoalAtas()));
                soal_bawah.setText(Html.fromHtml(soals.get(index).getSoalBawah()));
                a.setText(Html.fromHtml(soals.get(index).getA()));
                b.setText(Html.fromHtml(soals.get(index).getB()));
                c.setText(Html.fromHtml(soals.get(index).getC()));
                d.setText(Html.fromHtml(soals.get(index).getD()));
                dialogs.dismiss();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void njawab(String jwb, Integer index){
        if(findJawaban(soals.get(index).getNo()).equals("x")){
            Jawaban jawaban = new Jawaban(soals.get(index).getNo().toString(), jwb, soals.get(index).getKunci());
            jawabans.add(jawaban);
            if(jawaban.getKoreksi()){
                nilai = nilai + 4;
            }
        }else{
            if(findJawabanObject(soals.get(index).getNo()).getKoreksi()){
                nilai = nilai - 4;
            }
            jawabans.remove(index);

            Jawaban jawaban = new Jawaban(soals.get(index).getNo().toString(), jwb, soals.get(index).getKunci());
            jawabans.add(jawaban);
            if(jawaban.getKoreksi()){
                nilai = nilai + 4;
            }
        }
        terjawabs.add(index);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public String findJawaban(Integer in){
        String jaw="x";
        if(jawabans.size() > 0) {
            for (Jawaban jawaban : jawabans) {
                if (jawaban.getNo().equals(in.toString())) {
                    jaw = jawaban.getJawaban();
                    setWarnaJawaban(jaw);
                } else {
                    setWarnaJawaban("x");
                }
            }
        }else{
            setWarnaJawaban("x");
        }
        setWarnaJawaban(jaw);
        return jaw;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Jawaban findJawabanObject(Integer in){
        Jawaban jaw = null;
        if(jawabans.size() > 0) {
            for (Jawaban jawaban : jawabans) {
                if (jawaban.getNo().equals(in.toString())) {
                    jaw = jawaban;
                }
            }
        }
        return jaw;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setWarnaJawaban(String jawaban){
        if(jawaban.equals("a")){
            aa.setBackground(getResources().getDrawable(R.drawable.bg_rounded_orange));
            bb.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            cc.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            dd.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
        }else if(jawaban.equals("b")){
            aa.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            bb.setBackground(getResources().getDrawable(R.drawable.bg_rounded_orange));
            cc.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            dd.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
        }else if(jawaban.equals("c")){
            aa.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            bb.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            cc.setBackground(getResources().getDrawable(R.drawable.bg_rounded_orange));
            dd.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
        }else if(jawaban.equals("d")){
            aa.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            bb.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            cc.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            dd.setBackground(getResources().getDrawable(R.drawable.bg_rounded_orange));
        }else {
            aa.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            bb.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            cc.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
            dd.setBackground(getResources().getDrawable(R.drawable.bg_rounded_gray));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                String petunjuk = loadJSONFromAsset(SoalActivity.this, "petunjuk.txt");
                final AlertDialog.Builder dialog = new AlertDialog.Builder(SoalActivity.this);
                dialog.setMessage(Html.fromHtml(petunjuk));
                dialog.setPositiveButton("Ya, Saya mengerti", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();

                return true;

            case android.R.id.home:
                this.finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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


    public void initToolbar(Spanned vTitle) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View viewActionBar = getLayoutInflater().inflate(R.layout.toolbar_title, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);
        tvTitle = viewActionBar.findViewById(R.id.text_title);
        tvTitle.setText(vTitle);
        getSupportActionBar().setCustomView(viewActionBar, params);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
    }
}
