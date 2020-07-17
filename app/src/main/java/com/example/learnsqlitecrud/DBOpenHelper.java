package com.example.learnsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "itemBase.db";
    public static final String TABLE_ITEMS = "items";

    public DBOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE myTable " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstItem TEXT, " +
                "secondItem TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS myTable");
        onCreate(db);
    }
}
