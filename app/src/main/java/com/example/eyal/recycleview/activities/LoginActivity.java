package com.example.eyal.recycleview.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eyal.recycleview.R;
import com.example.eyal.recycleview.bl.*;
import com.example.eyal.recycleview.common.*;

//Start the app on LoginActivity
public class LoginActivity extends Activity {

	private EditText userNameEditText;
	private EditText passwordEditText;
	private EditText phoneNumberEditText;
	private UsersController controller;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        controller = new UsersController(this);
        //ask the controller if the user is logged in.
        if(controller.isLoggedIn())
        {
        	//In case the user is logged in start the main activity.
			startMembersActivity();
        	return;
        }
        //get the useName and password edit text view 
        userNameEditText = (EditText) findViewById(R.id.editTextUserName);
        passwordEditText = (EditText) findViewById(R.id.editTextPassword);
		phoneNumberEditText = (EditText) findViewById(R.id.editTextphoneNumber);
    }
    
    public void logInClicked(View v)
    {
    	//get the password, user name and phone number from the edit text.
    	if(userNameEditText!=null && passwordEditText!=null && phoneNumberEditText!=null)
    	{
    		String userName  = userNameEditText.getText().toString();
    		String pass = passwordEditText.getText().toString();
			String phoneNumber = phoneNumberEditText.getText().toString();
			if(controller.isListEmpty())
			{
				try {
					User u = controller.AddUser(userName, pass, phoneNumber);
					controller.setLogedIn(u);
					startMembersActivity();
					return;
				}
				catch(Exception e)
				{}
			}
			else {
				User u = controller.GetUser(userName, pass, phoneNumber);
				//the user is exists, set the IsLogin flag to true.
				if (u != null) {
					controller.setLogedIn(u);
					startMembersActivity();
					return;
				}
				//log in was failed.
				Toast.makeText(this, "User name or password or phone number is incorrect", Toast.LENGTH_LONG).show();
			}
    	}
		
	}
    
    public void startMembersActivity()
    {
		//Explicit intent.
		Intent  i = new Intent(this,MembersActivity.class);
		//Start the activity
		startActivity(i);
		finish();
    }
}
