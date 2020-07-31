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
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Integer idItem = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.ID));
                String firstItem = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLS_1));
                String secondItem = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLS_2));

                Item item = new Item();
                item.id = idItem;
                item.firstItem = firstItem;
                item.secondItem = secondItem;

                items.add(item);
                cursor.moveToNext();
            }
            return items;
        }

    }

    Item getItem(int id) {
        try (Cursor cursor = db.query(DBOpenHelper.TABLE_ITEMS,
                null,
                DBOpenHelper.ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        )) {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            Integer idItem = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.ID));
            String firstItem = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLS_1));
            String secondItem = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COLS_2));

            Item item = new Item();
            item.id = idItem;
            item.firstItem = firstItem;
            item.secondItem = secondItem;

            return item;
        }
    }


    public void updateItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COLS_1, item.firstItem);
        values.put(DBOpenHelper.COLS_2, item.secondItem);
        db.update(DBOpenHelper.TABLE_ITEMS,
                values,
                DBOpenHelper.ID + " = ?",
                new String[]{String.valueOf(item.id)}
        );
        db.close();
    }

    public void deleteItem(int id) {
        db.delete(DBOpenHelper.TABLE_ITEMS,
                DBOpenHelper.ID + " = ?",
                new String[]{String.valueOf(id)}
        );
        db.close();
    }
}
