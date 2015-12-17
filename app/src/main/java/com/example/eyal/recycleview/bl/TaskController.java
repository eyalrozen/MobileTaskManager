package com.example.eyal.recycleview.bl;

import android.content.Context;
import android.util.Log;

import com.example.eyal.recycleview.common.*;
import com.example.eyal.recycleview.dal.*;
import com.example.eyal.recycleview.activities.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eyal on 11/7/2015.
 */
public class TaskController implements ITaskController {
    private IDataAcces dao;
    private Context context;
    //observers list.
    private List<OnDataSourceChangeListener> dataSourceChangedListenrs = new ArrayList<OnDataSourceChangeListener>();
    public TaskController(Context context)
    {
        this.context = context;
        dao = DAO.getInstance(context.getApplicationContext());
    }
    private List<String> descriptionList;

    public List<TaskItem> GetTaskList() {
        try{
            List<TaskItem> list = dao.GetTaskList();
            return list;
        }
        catch(Exception e)
        {
            return new ArrayList<TaskItem>();
        }

    }
    public void AddTask(TaskItem task)
    {
        try {
            //add the friend to the data base and use the returned friend and add it ti the local cache.
            //the friend that returned from the DAO contain the id of the entity.
            TaskItem retTask = dao.AddTask(task);
            if(retTask == null) return;
            //update what ever it will be.
            invokeDataSourceChanged();
        } catch (Exception e) {
            Log.e("TaskController", e.getMessage());
        }
    }

    public void registerOnDataSourceChanged(OnDataSourceChangeListener listener)
    {
        if(listener!=null)
            dataSourceChangedListenrs.add(listener);
    }
    public void unRegisterOnDataSourceChanged(OnDataSourceChangeListener listener)
    {
        if(listener!=null)
            dataSourceChangedListenrs.remove(listener);
    }
    public void invokeDataSourceChanged()
    {
        for (OnDataSourceChangeListener listener : dataSourceChangedListenrs) {
            listener.DataSourceChanged();
        }
    }

    public void createAlarm(String message,int  secondsFromNow)
    {
        AlarmHelper.setAlarm(context,secondsFromNow,message);
    }

    public void removeAlarm(Context context,int alarmID)
    {
        AlarmHelper.cancelAlarm(context,alarmID);
    }
}
