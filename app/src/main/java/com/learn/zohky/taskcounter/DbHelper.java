package com.learn.zohky.taskcounter;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

    private final static String DB_NAME = "myDataBase.db";
    private final static int DB_VERS = 1;
    public final static String TABLE_NAME = "tasks";
    public final static String ID = "_id";
    public final static String TASK_NAME = "name";
    public final static String TASK_COUNT = "count";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTasks =
                "CREATE TABLE " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TASK_NAME + " TEXT NOT NULL, " +
                TASK_COUNT + " INTEGER);";
        try{
            db.execSQL(createTableTasks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
