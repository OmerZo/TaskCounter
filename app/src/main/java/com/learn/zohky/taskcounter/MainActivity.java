package com.learn.zohky.taskcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> taskList;
    TaskAdapter adapter;
    TasksHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper helper = new DbHelper(this);
        handler = new TasksHandler(helper);
        taskList = (ArrayList<Task>) handler.getAll();

        adapter = new TaskAdapter(this, taskList);
        ListView lvTasks = (ListView)findViewById(R.id.lvTasks);
        lvTasks.setAdapter(adapter);
    }

    public void addTask(View view) {
        EditText etNewTask = (EditText)findViewById(R.id.etNewTask);
        String taskName = etNewTask.getText().toString();
        if(!handler.checkIfExist(taskName)){
            handler.insert(new Task(0, taskName, 0));
            Task task = handler.getByName(taskName);
            adapter.add(task);
            etNewTask.setText("");
        } else {
            Toast.makeText(this, "This task already exist", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ListView lvTasks = (ListView)findViewById(R.id.lvTasks);
        for (int i = 0; i < lvTasks.getChildCount(); i++) {
            View childAt = lvTasks.getChildAt(i);
            TextView tvTaskName = (TextView)childAt.findViewById(R.id.tvTaskName);
            TextView tvCount = (TextView)childAt.findViewById(R.id.tvTaskCount);
            handler.updateCount(new Task(0,tvTaskName.getText().toString(), Integer.valueOf(tvCount.getText().toString())));
        }
    }
}
/*
1) delete tasks.
2) edit task name and task count.
3) save in calender every count and display it later.
 */
