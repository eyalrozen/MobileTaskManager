package com.example.eyal.recycleview.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eyal.recycleview.R;

public class addtaskActivity extends AppCompatActivity  {
    private Button createBtn;
    private EditText dscText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        createBtn = (Button) findViewById(R.id.createBtn);
        dscText = (EditText) findViewById(R.id.description);
        createBtn.setOnClickListener(OnCreateBtnClickListener);


    }

    private View.OnClickListener OnCreateBtnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           if (dscText.getText().toString() != "") {
                //DAO.getInstance().AddTask(dscText.getText().toString());
               Intent resultIntent=new Intent();
               resultIntent.putExtra("task", dscText.getText().toString());
               setResult(Activity.RESULT_OK, resultIntent);
               finish();
            }

        }

        ;

    };
}
