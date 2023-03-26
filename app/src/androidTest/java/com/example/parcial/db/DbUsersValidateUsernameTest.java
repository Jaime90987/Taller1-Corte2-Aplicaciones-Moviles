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
public class DbUsersValidateUsernameTest extends TestCase {
    private DbUsers dbUsers;

    @Before
    public void onBefore() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        dbUsers = new DbUsers(context);
        SQLiteDatabase db = dbUsers.getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put(COLUMN_USER, "Username1");
        values1.put(COLUMN_PASS, "12345");
        db.insert(DATABASE_TABLE_USERS, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put(COLUMN_USER, "Username2");
        values2.put(COLUMN_PASS, "54321");
        db.insert(DATABASE_TABLE_USERS, null, values2);
    }

    @After
    public void onAfter() {
        dbUsers.close();
    }

    @Test
    public void testValidateUsernameWhenExistsInDB() {
        boolean result = dbUsers.validateUsername("Username1");
        assert (result);
    }

    @Test
    public void testValidateUsernameWhenDoNotExistsInDB() {
        boolean result = dbUsers.validateUsername("Username3");
        assert (!result);
    }

}