package com.example.parcial.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbUsers extends DbHelper {
    Context context;

    public DbUsers(@Nullable Context context) {
        super(context);
    }

    public void addUser(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, name);
        values.put(COLUMN_PASS, password);

        try {
            db.insert(DATABASE_TABLE_NAME, null, values);
        } catch (SQLException e) {
            Log.e("INFO", "Error al insertar datos en la base de datos: " + e.getMessage());
            Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
