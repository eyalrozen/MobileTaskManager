package com.example.eyal.recycleview.dal;

import android.provider.BaseColumns;
/**
 * Created by Eyal on 11/21/2015.
 */
public class TaskDBContract {
    public static final class TaskEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "Tasks";

        public static final String COLUMN_TASK_DESCRIPTION = "Task_description";



    }
}
