package com.example.eyal.recycleview.bl;

import android.content.Context;

import com.example.eyal.recycleview.common.*;

import java.util.List;

/**
 * Created by Eyal on 11/7/2015.
 */
public interface ITaskController
{
    List<TaskItem> GetTaskList();
    void AddTask(TaskItem task);
    void createAlarm(String message,int  secondsFromNow);
    void removeAlarm(Context context,int alarmID);


}
