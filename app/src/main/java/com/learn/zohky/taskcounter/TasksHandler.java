package com.learn.zohky.taskcounter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

public class TasksHandler {

    private DbHelper helper;

    public TasksHandler(DbHelper helper){
        this.helper = helper;
    }

    public void insert(Task task){
        SQLiteDatabase db = helper.getWritableDatabase();
        String createTask =
                "INSERT INTO " + DbHelper.TABLE_NAME +
                " VALUES ( null, '" + task.getTaskName() + "', " + task.getTaskCount() + ");";
        db.execSQL(createTask);
        db.close();
        helper.close();
    }

    public Collection<Task> getAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        String getAll = "SELECT * FROM " + DbHelper.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(getAll,null);
        if(cursor.moveToFirst()){
            do{
                taskArrayList.add(createTask(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        helper.close();
        return taskArrayList;
    }

    public Task getByName(String name){
        SQLiteDatabase db = helper.getReadableDatabase();
        String getById = "SELECT * FROM " + DbHelper.TABLE_NAME + " WHERE " + DbHelper.TASK_NAME + " = '" + name + "';";
        Cursor cursor = db.rawQuery(getById, null);
        cursor.moveToNext();
        Task task = createTask(cursor);
        cursor.close();
        db.close();
        helper.close();
        return task;
    }

    public void updateCount(Task task){
        SQLiteDatabase db = helper.getWritableDatabase();
        String updateCount =
                "UPDATE " + DbHelper.TABLE_NAME + " SET " + DbHelper.TASK_COUNT + " = " + task.getTaskCount()
                + " WHERE " + DbHelper.TASK_NAME + " = '" + task.getTaskName() + "';";
        db.execSQL(updateCount);
        db.close();
        helper.close();
    }

    public boolean checkIfExist(String name){
        SQLiteDatabase db = helper.getReadableDatabase();
        String checkIfExist = "SELECT * FROM " + DbHelper.TABLE_NAME + " WHERE " + DbHelper.TASK_NAME + " = '" + name + "';";
        Cursor cursor = db.rawQuery(checkIfExist, null);
        boolean flag = cursor.moveToFirst();
        cursor.close();
        db.close();
        helper.close();
        return flag;
    }

    @NonNull
    private Task createTask(Cursor cursor){
        return new Task(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
    }
}
