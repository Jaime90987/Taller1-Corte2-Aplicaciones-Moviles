package com.example.parcial.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbProducts extends DbHelper {
    Context context;

    public DbProducts(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void addProduct(long id, String name, int quantity, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID_PRODUCT, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_PRICE, price);

        try {
            db.insert(DATABASE_TABLE_PRODUCTS, null, values);
        } catch (SQLException e) {
            Log.e("INFO", "Error al insertar datos en la base de datos: " + e.getMessage());
            Toast.makeText(context, "Error al registrar el producto", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
