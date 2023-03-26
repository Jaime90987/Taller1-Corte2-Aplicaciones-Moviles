package com.example.parcial.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Productos.db";
    private static final int DATABASE_VERSION = 17;
    public static final String DATABASE_TABLE_PRODUCTS = "Productos";
    public static final String DATABASE_TABLE_USERS = "Usuarios";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ID_PRODUCT = "id_product";
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
        db.execSQL("CREATE TABLE " + DATABASE_TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID_PRODUCT + " INTEGER NOT NULL ," +
                COLUMN_NAME + " TEXT  NOT NULL," +
                COLUMN_QUANTITY + " INTEGER NOT NULL," +
                COLUMN_PRICE + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + DATABASE_TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER + " TEXT UNIQUE NOT NULL," +
                COLUMN_PASS + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USERS);
        onCreate(db);
    }
}
