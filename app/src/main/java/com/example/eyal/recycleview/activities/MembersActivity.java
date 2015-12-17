package com.example.eyal.recycleview.activities;

import android.os.Bundle;

import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import com.example.eyal.recycleview.*;
import com.example.eyal.recycleview.R;
import com.example.eyal.recycleview.bl.*;
import com.example.eyal.recycleview.common.*;


public class MembersActivity extends AppCompatActivity implements
        OnDataSourceChangeListener {
    private ImageButton FAB;
    private Button addBtn;
    private RecyclerView mRecyclerView;
    private UsersAdapter uAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private UsersController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
        //create the controller.
        controller = new UsersController(this);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        controller.registerOnDataSourceChanged(this);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        uAdapter = new UsersAdapter(controller.GetUsersList());
        mRecyclerView.setAdapter(uAdapter);
        addBtn =(Button) findViewById(R.id.addBtn);
       // addBtn.setOnClickListener(OnAddBtnClickListener);
    }

   /* private View.OnClickListener OnAddBtnClickListener = new View.OnClickListener(){
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
    }*/

    @Override
    public void DataSourceChanged() {
        if (uAdapter != null) {
            uAdapter.UpdateDataSource(controller.GetUsersList());
            uAdapter.notifyDataSetChanged();
        }

    }
}
