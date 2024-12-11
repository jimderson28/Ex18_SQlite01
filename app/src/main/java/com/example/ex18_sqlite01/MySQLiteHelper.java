package com.example.ex18_sqlite01;

import android.content.Context;
import android.content.SyncRequest;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MySQLiteHelper extends SQLiteOpenHelper{
    private static final String DATABBASE_NAME = "comments.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COMMENT = "comment";

    private static final String DATABASE_CREATE = "create  table"
            + TABLE_COMMENTS+"(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + "text not null);";
    public MySQLiteHelper(Context context){
        super(context, DATABBASE_NAME,null,DATABASE_VERSION);
    }
    public MySQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.w("jpyMsg","call onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_CREATE);
        Log.w("jpyMsg",
                "call onCreate");
    }
}
