package com.example.eyal.recycleview.dal;

import com.example.eyal.recycleview.common.*;

import java.util.List;

/**
 * Created by Eyal on 11/7/2015.
 */
public interface IDataAcces {
    User AddUser(User usr);
    String GetTeamName();
    List<TaskItem> GetTaskList();
    List<User> GetUserList();
    void RemoveTask(TaskItem task);
    TaskItem AddTask(TaskItem task);
}
