package com.cokilabs.karakter.latihansoal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "kbi";

    // Login table name
    private static final String TABLE_NAME = "soal";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOMOR = "nomor";
    private static final String KEY_SOAL_ATAS = "soal_atas";
    private static final String KEY_SOAL_BAWAH = "soal_bawah";
    private static final String KEY_A = "a";
    private static final String KEY_B = "b";
    private static final String KEY_C = "c";
    private static final String KEY_D = "d";
    private static final String KEY_KUNCI = "kunci";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // TODO Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_SESSION_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOMOR + " INTEGER ,"
                + KEY_SOAL_ATAS + " TEXT,"
                + KEY_SOAL_BAWAH + " TEXT,"
                + KEY_A + " TEXT,"
                + KEY_B + " TEXT,"
                + KEY_C + " TEXT,"
                + KEY_D + " TEXT,"
                + KEY_KUNCI + " VARCHAR"
                + ")";

        db.execSQL(CREATE_SESSION_TABLE);
        Log.d("sql", CREATE_SESSION_TABLE);
        Log.d(TAG, "Database tables created");
    }

    // TODO Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        Log.d(TAG, "Database tables updated");
        onCreate(db);
    }



}