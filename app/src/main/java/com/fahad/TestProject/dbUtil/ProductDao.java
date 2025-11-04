package com.fahad.TestProject.dbUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fahad.TestProject.Entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private SqlDB dbHelper;

    public ProductDao(Context context) {
        dbHelper = new SqlDB(context);
    }

    // 🔹 INSERT
    public long insert(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SqlDB.COL_NAME, product.getName());
        values.put(SqlDB.COL_EMAIL, product.getEmail());
        values.put(SqlDB.COL_PRICE, product.getPrice());
        values.put(SqlDB.COL_QUANTITY, product.getQuantity());
        values.put("IMAGE_URI", product.getImageUri()); // new field

        long id = db.insert(SqlDB.TBL_NAME, null, values);
        db.close();
        return id;
    }

    // 🔹 READ ALL
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + SqlDB.TBL_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Product p = new Product(
                        cursor.getInt(cursor.getColumnIndexOrThrow("ID")),
                        cursor.getString(cursor.getColumnIndexOrThrow(SqlDB.COL_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(SqlDB.COL_EMAIL)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(SqlDB.COL_PRICE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(SqlDB.COL_QUANTITY)),
                        cursor.getString(cursor.getColumnIndexOrThrow("IMAGE_URI"))
                );

                list.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    // 🔹 GET PRODUCT BY ID (for edit)
    public Product getProductById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SqlDB.TBL_NAME + " WHERE ID = ?", new String[]{String.valueOf(id)});
        Product p = null;
        if (cursor.moveToFirst()) {
            p = new Product(
                    cursor.getInt(cursor.getColumnIndexOrThrow("ID")),
                    cursor.getString(cursor.getColumnIndexOrThrow(SqlDB.COL_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(SqlDB.COL_EMAIL)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(SqlDB.COL_PRICE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(SqlDB.COL_QUANTITY)),
                    cursor.getString(cursor.getColumnIndexOrThrow("IMAGE_URI"))
            );
        }
        cursor.close();
        db.close();
        return p;
    }

    // 🔹 UPDATE
    public int update(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SqlDB.COL_NAME, product.getName());
        values.put(SqlDB.COL_EMAIL, product.getEmail());
        values.put(SqlDB.COL_PRICE, product.getPrice());
        values.put(SqlDB.COL_QUANTITY, product.getQuantity());
        values.put("IMAGE_URI", product.getImageUri()); // new field

        int rows = db.update(SqlDB.TBL_NAME, values, "ID = ? ", new String[]{String.valueOf(product.getId())});
        db.close();
        return rows;
    }

    // 🔹 DELETE
    public int delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete(SqlDB.TBL_NAME, "ID = ?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}