package com.gvenet.mby.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gvenet.mby.pojos.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends DAO {

    public ItemDAO(Context context) {
        super(new ItemDBHelper(context));
    }

    public Item find(Long id) {
        Item item = null;

        open();

        Cursor cursor = db.rawQuery("SELECT * " +
                        "FROM " + ItemDBHelper.MBY_TABLE_NAME +
                        " WHERE " + ItemDBHelper.MBY_KEY + " = ?",
                new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            item = new Item();
            cursor.getLong(1);
            item.setId(cursor.getLong(ItemDBHelper.MBY_KEY_COLUMN_INDEX));
            item.setTitle(cursor.getString(ItemDBHelper.MBY_TITLE_COLUMN_INDEX));
            item.setDescription(cursor.getString(ItemDBHelper.MBY_DESCRIPTION_COLUMN_INDEX));
            item.setUrl(cursor.getString(ItemDBHelper.MBY_URL_COLUMN_INDEX));
            item.setType(cursor.getString(ItemDBHelper.MBY_TYPE_COLUMN_INDEX));
        }

        close();

        return item;
    }

    public List<Item> List() {
        List<Item> items = new ArrayList<>();

        open();

        Cursor cursor = db.rawQuery("SELECT * FROM " + ItemDBHelper.MBY_TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Item item = new Item();
                item.setId(cursor.getLong(ItemDBHelper.MBY_KEY_COLUMN_INDEX));
                item.setTitle(cursor.getString(ItemDBHelper.MBY_TITLE_COLUMN_INDEX));
                item.setDescription(cursor.getString(ItemDBHelper.MBY_DESCRIPTION_COLUMN_INDEX));
                item.setUrl(cursor.getString(ItemDBHelper.MBY_URL_COLUMN_INDEX));
                item.setType(cursor.getString(ItemDBHelper.MBY_TYPE_COLUMN_INDEX));

                items.add(item);

                cursor.moveToNext();
            }
        }
        close();

        return items;
    }

    public void add(Item item) {
        open();

        ContentValues values = new ContentValues();
        values.put(ItemDBHelper.MBY_TITLE, item.getTitle());
        values.put(ItemDBHelper.MBY_DESCRIPTION, item.getDescription());
        values.put(ItemDBHelper.MBY_URL, item.getUrl());
        values.put(ItemDBHelper.MBY_TYPE, item.getType());


        Long id = db.insert(ItemDBHelper.MBY_TABLE_NAME, null, values);

        item.setId(id);

        close();
    }

    public void delete(Item item) {
        open();

        db.delete(ItemDBHelper.MBY_TABLE_NAME, ItemDBHelper.MBY_KEY + " = ?",
                new String[] {item.getId().toString()});

        close();
    }
}


