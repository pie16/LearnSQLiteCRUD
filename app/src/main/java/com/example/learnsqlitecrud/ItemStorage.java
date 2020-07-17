package com.example.learnsqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ItemStorage {

    private SQLiteDatabase db;

    public ItemStorage(Context context) {
        db = new DBOpenHelper(context.getApplicationContext()) //используем контекст приложения,
                // что-бы не держать активности и сборщик мусора мог их удалять
                .getWritableDatabase();
    }

    public void create(Item item) {
        ContentValues values = new ContentValues();
        values.put("firstItem", item.firstItem);
        values.put("secondItem", item.secondItem);

        db.insertOrThrow(DBOpenHelper.TABLE_ITEMS, null, values);
    }

}
