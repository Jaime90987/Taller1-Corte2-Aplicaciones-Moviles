package com.example.parcial.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbUsers extends DbHelper {
    Context context;
    SQLiteDatabase db;

    public DbUsers(@Nullable Context context) {
        super(context);
    }

    public void addUser(String username, String password) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, username);
        values.put(COLUMN_PASS, password);

        try {
            db.insert(DATABASE_TABLE_USERS, null, values);
        } catch (SQLException e) {
            Log.e("INFO", "Error al insertar datos en la base de datos: " + e.getMessage());
            Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public boolean validateUsername(String username) {
        db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursorUsername = db.rawQuery("SELECT " + COLUMN_USER + " FROM " + DATABASE_TABLE_USERS + " WHERE " + COLUMN_USER + " = ? ", new String[]{username});
        return cursorUsername.getCount() > 0;
    }

    @SuppressLint("Range")
    public int getId(String username, String password) {
        db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID};
        String selection = "username = ? AND password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(DATABASE_TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        int id = -1;

        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        }

        cursor.close();
        db.close();

        return id;
    }
}
