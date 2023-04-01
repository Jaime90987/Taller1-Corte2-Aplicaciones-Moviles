package com.example.parcial.db;

import static com.example.parcial.db.DbHelper.COLUMN_ID_PRODUCT;
import static com.example.parcial.db.DbHelper.COLUMN_NAME;
import static com.example.parcial.db.DbHelper.DATABASE_TABLE_PRODUCTS;

import static org.junit.Assert.assertArrayEquals;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


@RunWith(AndroidJUnit4.class)
public class DbProductsTest extends TestCase {
    DbProducts dbProducts;
    SQLiteDatabase db;

    @Before
    public void onBefore() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbProducts = new DbProducts(context);
        db = dbProducts.getWritableDatabase();

        db.execSQL(" DELETE FROM " + DATABASE_TABLE_PRODUCTS);
        db.execSQL(" DELETE FROM sqlite_sequence WHERE name = 'Productos'");

        dbProducts.addProduct(1, "productName1", 5, "120");
        dbProducts.addProduct(2, "productName2", 7, "5040");
        dbProducts.addProduct(3, "productName3", 0, "1");
        db.close();
    }

    @Test
    public void testAddProductId() {
        db = dbProducts.getReadableDatabase();
        List<Integer> listProductId = new ArrayList<>();
        Cursor cursor = db.rawQuery(" SELECT " + COLUMN_ID_PRODUCT + " FROM " + DATABASE_TABLE_PRODUCTS, null);

        if (cursor.moveToFirst()) {
            do {
                listProductId.add(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCT)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        dbProducts.close();

        List<Integer> listExpected = new ArrayList<>();
        listExpected.add(1);
        listExpected.add(2);
        listExpected.add(3);

        assertArrayEquals(listExpected.toArray(), listProductId.toArray());
    }

    @Test
    public void testAddProductNames() {
        db = dbProducts.getReadableDatabase();
        List<String> listProductNames = new ArrayList<>();
        Cursor cursor = db.rawQuery(" SELECT " + COLUMN_NAME + " FROM " + DATABASE_TABLE_PRODUCTS, null);

        if (cursor.moveToFirst()) {
            do {
                listProductNames.add(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        dbProducts.close();

        List<String> listExpected = new ArrayList<>();
        listExpected.add("productName1");
        listExpected.add("productName2");
        listExpected.add("productName3");

        assertArrayEquals(listExpected.toArray(), listProductNames.toArray());
    }
}