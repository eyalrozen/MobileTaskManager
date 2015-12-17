package com.example.eyal.recycleview.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eyal.recycleview.R;
import com.example.eyal.recycleview.bl.*;
import com.example.eyal.recycleview.common.*;
import com.example.eyal.recycleview.dal.*;
import com.example.eyal.recycleview.common.OnDataSourceChangeListener;

public class MainActivity extends AppCompatActivity implements
        OnDataSourceChangeListener {

    private Button addBtn;
    private RecyclerView mRecyclerView;
    private TaskAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TaskController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
        //create the controller.
        controller = new TaskController(this);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        controller.registerOnDataSourceChanged(this);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TaskAdapter(controller.GetTaskList());
        mRecyclerView.setAdapter(mAdapter);
        addBtn =(Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(OnAddBtnClickListener);
    }

    private View.OnClickListener OnAddBtnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent nextScreen = new Intent(getApplicationContext(), addtaskActivity.class);
            startActivityForResult(nextScreen, 1);
        }
    };

    @Override
    //Recieve the input text from addtaskActivity and insert it as new task
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {

                    String newTask = data.getStringExtra("task");
                    TaskItem t = new TaskItem(newTask);
                    controller.AddTask(t);
                    mAdapter.notifyDataSetChanged();
                    controller.createAlarm(newTask,5);
                    controller.removeAlarm(this,001);
                }
                break;
            }
        }
    }

    @Override
    public void DataSourceChanged() {
        if (mAdapter != null) {
            mAdapter.UpdateDataSource(controller.GetTaskList());
            mAdapter.notifyDataSetChanged();
        }

    }
}
