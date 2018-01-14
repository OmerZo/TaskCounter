package com.learn.zohky.taskcounter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> implements View.OnClickListener {

    Task task;

    public TaskAdapter(@NonNull Context context, List<Task> taskList) {
        super(context, 0, taskList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        task = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.task_item, parent, false);
        }

        TextView tvTaskName = (TextView) convertView.findViewById(R.id.tvTaskName);
        TextView tvTaskCount = (TextView) convertView.findViewById(R.id.tvTaskCount);
        tvTaskName.setText(task.getTaskName());
        tvTaskCount.setText(task.getTaskCount() + "");
        Button bAdd = (Button)convertView.findViewById(R.id.bAdd);
        bAdd.setTag(task);
        bAdd.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Button bAdd = (Button)v;
        Task task = (Task) bAdd.getTag();
        task.setTaskCount(task.getTaskCount() + 1);
        notifyDataSetChanged();
    }
}
