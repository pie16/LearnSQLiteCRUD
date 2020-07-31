package com.example.learnsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "itemBase.db";
    public static final String TABLE_ITEMS = "items";
    public static final String ID = "_id";
    public static final String COLS_1 = "firstItem";
    public static final String COLS_2 = "secondItem";


    public DBOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_ITEMS + "(" +
                ID + " integer primary key autoincrement, " +
                COLS_1 + ", " +
                COLS_2 +
                ")";
                db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_ITEMS);
        onCreate(db);
    }
}
