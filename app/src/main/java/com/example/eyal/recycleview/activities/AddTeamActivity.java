package com.example.eyal.recycleview.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eyal.recycleview.R;
import com.example.eyal.recycleview.bl.UsersController;
import com.example.eyal.recycleview.common.User;

public class AddTeamActivity extends Activity {

	private EditText teamNameEditText;
	private Button AddButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteam);

        //get the team name
        teamNameEditText = (EditText) findViewById(R.id.teamName);
		AddButton = (Button) findViewById(R.id.addBtn);

    }

	public void OnAddBtnClick(View v)
	{
        if (teamNameEditText.getText().toString() != "") {
            //DAO.getInstance().AddTask(dscText.getText().toString());
            Intent resultIntent=new Intent();
            resultIntent.putExtra("teamName", teamNameEditText.getText().toString());
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
	}

}
