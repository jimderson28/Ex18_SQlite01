package com.example.ex18_sqlite01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.core.app.NavUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentsDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String [] allColumns = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT
    };

    public CommentsDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){dbHelper.close();}

    public Comment creatComment(String comment){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT,comment);
        long insertId = database.
                insert(MySQLiteHelper.TABLE_COMMENTS,null,values);

        Cursor cursor = database.query
                (MySQLiteHelper.TABLE_COMMENTS,
                        allColumns,MySQLiteHelper.COLUMN_ID +" = " + insertId,
                        null,null,null,null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteComment(Comment comment){
        long id = comment.getId();
        System.out.println("comment delete with id" + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS,MySQLiteHelper.COLUMN_ID + "=" +
                id,null);
    }

    public List<Comment> getAllComments(){
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,allColumns,null,null
                , null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        cursor.close();
        return comments;

    }

    private Comment cursorToComment(Cursor cursor){
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment((cursor.getString(1)));
        return comment;
    }

    public Comment addCommentsToSqlite(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);

        long insertid = database.
                insert(MySQLiteHelper.TABLE_COMMENTS, null,values);
        String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_COMMENT};
        Cursor cursor = database.query
                (MySQLiteHelper.TABLE_COMMENTS,
                        allColumns, MySQLiteHelper.
                                COLUMN_ID+ " = " + insertid,null,
                        null,null,null);

        Comment newcomment = cursorToComment(cursor);
        cursor.moveToFirst();
        cursor.close();
        return newcomment;
    }

    public void addCommentsToSqlite(Comment comment) {
        addCommentsToSqlite(comment);
    }

    public void addCommentsToSqlite(ArrayList<Comment> mList) {
        for (Comment cm : mList ){
            addCommentsToSqlite(cm);
        }
    }

}
