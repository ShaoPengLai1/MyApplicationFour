package com.bawei.shaopenglai.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SqlitHelper extends SQLiteOpenHelper {

    public SqlitHelper(@Nullable Context context) {
        super(context, "Water.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table waters(id integer primary key autoincrement," +
                "name text," +
                "uuid text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
