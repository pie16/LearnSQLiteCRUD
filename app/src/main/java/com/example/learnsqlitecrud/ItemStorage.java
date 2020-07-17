package com.example.learnsqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ItemStorage {

    private SQLiteDatabase db;

    public ItemStorage(Context context) {
        db = new DBOpenHelper(context.getApplicationContext()) //используем контекст приложения,
                // что-бы не держать активности и сборщик мусора мог их удалять
                .getWritableDatabase();
    }

    public void createItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLS_1, item.firstItem);
        values.put(DBOpenHelper.COLS_2, item.secondItem);

        db.insertOrThrow(DBOpenHelper.TABLE_ITEMS, null, values);
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();

        try (Cursor cursor = db.query(DBOpenHelper.TABLE_ITEMS,
                null,
                null,
                null,
                null,
                null,
                null);) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String firstItem = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLS_1));
                String secondItem = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLS_2));

                Item item = new Item();
                item.firstItem = firstItem;
                item.secondItem = secondItem;

                items.add(item);
                cursor.moveToNext();
            }
            return items;
        }

    }
}
