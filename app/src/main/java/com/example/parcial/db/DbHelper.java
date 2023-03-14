package com.example.parcial.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Productos.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE_NAME = "Productos";
    public static final String DATABASE_TABLE_NAME2 = "Productos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "product_name";
    public static final String COLUMN_QUANTITY = "prodcut_quantity";
    public static final String COLUMN_PRICE = "prodcut_price";

    public static final String COLUMN_USER = "username";
    public static final String COLUMN_PASS = "password";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_QUANTITY + " INTEGER," +
                COLUMN_PRICE + " INTEGER)");

        db.execSQL("CREATE TABLE " + DATABASE_TABLE_NAME2 + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER + " TEXT," +
                COLUMN_PASS + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        onCreate(db);
    }
}
