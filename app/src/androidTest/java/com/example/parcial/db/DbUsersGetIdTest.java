package com.example.parcial.db;

import static com.example.parcial.db.DbHelper.COLUMN_PASS;
import static com.example.parcial.db.DbHelper.COLUMN_USER;
import static com.example.parcial.db.DbHelper.DATABASE_TABLE_USERS;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DbUsersGetIdTest extends TestCase {
    DbUsers dbUsers;
    long id1, id2;

    @Before
    public void onBefore() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbUsers = new DbUsers(context);
        SQLiteDatabase db = dbUsers.getWritableDatabase();

        db.execSQL(" DELETE FROM " + DATABASE_TABLE_USERS);
        db.execSQL(" DELETE FROM sqlite_sequence WHERE name = 'Usuarios'");

        ContentValues values1 = new ContentValues();
        values1.put(COLUMN_USER, "Username1");
        values1.put(COLUMN_PASS, "12345");
        id1 = db.insert(DATABASE_TABLE_USERS, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put(COLUMN_USER, "Username2");
        values2.put(COLUMN_PASS, "54321");
        id2 = db.insert(DATABASE_TABLE_USERS, null, values2);
    }

    @After
    public void onAfter() {
        dbUsers.close();
    }

    @Test
    public void testGetId1() {
        long result = dbUsers.getId("Username1", "12345");
        assertEquals(id1, result);
    }

    @Test
    public void testGetId2() {
        long result = dbUsers.getId("Username2", "54321");
        assertEquals(id2, result);
    }

    @Test
    public void testGetIdWhenRecordDoesNotExist() {
        long result = dbUsers.getId("Username3", "123");
        assertEquals(-1, result);
    }
}