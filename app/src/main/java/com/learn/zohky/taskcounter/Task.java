package com.learn.zohky.taskcounter;

public class Task {
    private int id;
    private String taskName;
    private int taskCount;

    public Task(int id, String taskName, int taskCount) {
        setId(id);
        setTaskName(taskName);
        setTaskCount(taskCount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
}
