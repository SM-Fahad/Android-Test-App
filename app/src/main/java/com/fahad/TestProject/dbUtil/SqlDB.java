package com.fahad.TestProject.dbUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlDB extends SQLiteOpenHelper {

    final static String NAME = "INVENTORY";

    final static String TBL_NAME = "PRODUCTS";
    final static String COL_NAME = "NAME";
    final static String COL_EMAIL = "EMAIL";
    final static String COL_PRICE = "PRICE";
    final static String COL_QUANTITY = "QUANTITY";


    public SqlDB(@Nullable Context context) {
        super(context, NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TBL_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, "
                + COL_EMAIL + " TEXT, " + COL_PRICE + " REAL, " + COL_QUANTITY + " INTEGER)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2){
            db.execSQL("ALTER TABLE " + TBL_NAME + " ADD COLUMN IMAGE_URI TEXT");
        }
    }


}
