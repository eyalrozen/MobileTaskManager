package com.example.eyal.recycleview.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.eyal.recycleview.activities.LoginActivity;
import com.example.eyal.recycleview.common.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eyal on 11/7/2015.
 */
public class DAO implements IDataAcces
{
    private List<TaskItem> TaskList = new ArrayList<TaskItem>();
    private static DAO instance;
    private Context context;
    private MembersDBHelper MembersdbHelper;
    private TaskDBHelper TaskdbHelper;
    private String[] membersColumns = {MembersDBContract.MembersEntry._ID,MembersDBContract.MembersEntry.COLUMN_MEMBER_USERNAME
    ,MembersDBContract.MembersEntry.COLUMN_MEMBER_PASSWORD,MembersDBContract.MembersEntry.COLUMN_MEMBER_PHONE,MembersDBContract.MembersEntry.COLUMN_MEMBER_MAILSENT
    ,MembersDBContract.MembersEntry.COLUMN_MEMBER_TEAM};
    private String[] tasksColumns = { TaskDBContract.TaskEntry._ID,
            TaskDBContract.TaskEntry.COLUMN_TASK_DESCRIPTION,};


    private DAO(Context context) {
        this.context = context;
        TaskdbHelper = new TaskDBHelper(this.context);
        MembersdbHelper = new MembersDBHelper(this.context);
    }

    /*
     * Single tone implement.
     */
    public static DAO getInstance(Context context)
    {
        if(instance ==  null)
            instance = new DAO(context);
        return instance;
    }

    public List<User> GetUserList()
    {
        SQLiteDatabase database = null;
        try {
            database = MembersdbHelper.getReadableDatabase();
            List<User> users = new ArrayList<User>();

            Cursor cursor = database.query(MembersDBContract.MembersEntry.TABLE_NAME, membersColumns,
                    null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user1 = cursorTouser(cursor);
                users.add(user1);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return users;
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    public List<TaskItem> GetTaskList() {
        SQLiteDatabase database = null;
        try {
            database = TaskdbHelper.getReadableDatabase();
            List<TaskItem> tasks = new ArrayList<TaskItem>();

            Cursor cursor = database.query(TaskDBContract.TaskEntry.TABLE_NAME, tasksColumns,
                    null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                TaskItem f = cursorToTask(cursor);
                tasks.add(f);
                cursor.moveToNext();
            }
            // make sure to close the cursor
            cursor.close();
            return tasks;
        } finally {
            if (database != null) {
                database.close();
            }
            else
                return null;
        }
    }

    private List<TaskItem> GenerateTaskList() {

        return TaskList;
    }

    public TaskItem AddTask(TaskItem task)
    {
        SQLiteDatabase database = null;
        try {
            database = TaskdbHelper.getReadableDatabase();
            if (task == null)
                return null;
            //build the content values. - add to db - need to do insert
            ContentValues values = new ContentValues();
            values.put(TaskDBContract.TaskEntry.COLUMN_TASK_DESCRIPTION, task.getDescription());


            //do the insert.
            long insertId = database.insert(TaskDBContract.TaskEntry.TABLE_NAME, null, values);

            //get the entity from the data base - extra validation, entity was insert properly.
            Cursor cursor = database.query(TaskDBContract.TaskEntry.TABLE_NAME, tasksColumns,
                    TaskDBContract.TaskEntry._ID + " = " + insertId, null, null, null, null);
            cursor.moveToFirst();
            //create the friend object from the cursor.
            TaskItem newTask = cursorToTask(cursor);
            cursor.close();
            return newTask;
        }finally {
            if (database != null)
                database.close();
        }
       /* TaskItem item = new TaskItem(task);
        TaskList.add(item);*/
    }

    private TaskItem cursorToTask(Cursor cursor) {
        TaskItem f = new TaskItem("");
        f.setId(cursor.getInt(cursor.getColumnIndex(TaskDBContract.TaskEntry._ID)));
        f.setDescription(cursor.getString(cursor
                .getColumnIndex(TaskDBContract.TaskEntry.COLUMN_TASK_DESCRIPTION)));

        return f;
    }

    private User cursorTouser(Cursor cursor) {
        User f = new User();
        f.setUserName(cursor.getString(cursor.getColumnIndex(MembersDBContract.MembersEntry.COLUMN_MEMBER_USERNAME)));
        f.setPassword(cursor.getString(cursor.getColumnIndex(MembersDBContract.MembersEntry.COLUMN_MEMBER_PASSWORD)));
        f.setPhoneNumber(cursor.getString((cursor.getColumnIndex(MembersDBContract.MembersEntry.COLUMN_MEMBER_PHONE))));
        f.setMailSent(cursor.getInt((cursor.getColumnIndex(MembersDBContract.MembersEntry.COLUMN_MEMBER_MAILSENT))));

        return f;
    }

    @Override
    public void RemoveTask(TaskItem task) {
        SQLiteDatabase database = null;
        try {
            database = TaskdbHelper.getReadableDatabase();
            long id = task.getId();
            database.delete(TaskDBContract.TaskEntry.TABLE_NAME, TaskDBContract.TaskEntry._ID + " = " + id,
                    null);
        }finally {
            if(database != null){
                database.close();
            }
        }
    }

    @Override
    public User AddUser(User usr) {
        SQLiteDatabase database = null;
        try {
            database = MembersdbHelper.getReadableDatabase();
            if (usr == null)
                return null;
            //build the content values. - add to db - need to do insert
            ContentValues values = new ContentValues();
            values.put(MembersDBContract.MembersEntry.COLUMN_MEMBER_USERNAME, usr.getUserName());
            values.put(MembersDBContract.MembersEntry.COLUMN_MEMBER_PASSWORD, usr.getPassword());
            values.put(MembersDBContract.MembersEntry.COLUMN_MEMBER_PHONE, usr.getPhoneNumber());
            values.put(MembersDBContract.MembersEntry.COLUMN_MEMBER_MAILSENT, ( usr.getMailSend()));
            values.put(MembersDBContract.MembersEntry.COLUMN_MEMBER_TEAM, (LoginActivity.teamName));
            Toast.makeText(context, usr.getUserName(), Toast.LENGTH_SHORT);

            //do the insert.
            long insertId = database.insert(MembersDBContract.MembersEntry.TABLE_NAME, null, values);

            //get the entity from the data base - extra validation, entity was insert properly.
            Cursor cursor = database.query(MembersDBContract.MembersEntry.TABLE_NAME, membersColumns,
                    MembersDBContract.MembersEntry._ID + " = " + insertId, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            //create the friend object from the cursor.
            User newUser = cursorTouser(cursor);
            cursor.close();
            return newUser;
        }finally {
            if (database != null)
                database.close();
        }

    }



}
