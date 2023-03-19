package com.gvenet.mby.DAO;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "mby.db";

    public static final String MBY_KEY = "id";
    public static final String MBY_TITLE = "title";
    public static final String MBY_DESCRIPTION = "description";
    public static final String MBY_URL = "url";
    public static final String MBY_TYPE = "genre";


    public static final String MBY_TABLE_NAME = "Mby";

    public static final int MBY_KEY_COLUMN_INDEX = 0;
    public static final int MBY_TITLE_COLUMN_INDEX = 1;
    public static final int MBY_DESCRIPTION_COLUMN_INDEX = 2;
    public static final int MBY_URL_COLUMN_INDEX = 3;
    public static final int MBY_TYPE_COLUMN_INDEX = 4;

    private static final String MBY_TABLE_CREATE =
            "CREATE TABLE " + MBY_TABLE_NAME + " (" +
                    MBY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MBY_TITLE + " TEXT, " +
                    MBY_DESCRIPTION + " TEXT," +
                    MBY_URL + " TEXT," +
                    MBY_TYPE + " TEXT)";



    public static final String MBY_TABLE_DROP = "DROP TABLE IF EXISTS " + MBY_TABLE_NAME + ";";

    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MBY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MBY_TABLE_DROP);
        onCreate(db);
    }
}
