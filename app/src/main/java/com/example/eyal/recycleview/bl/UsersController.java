package com.example.eyal.recycleview.bl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.eyal.recycleview.common.*;
import com.example.eyal.recycleview.dal.*;

import java.util.ArrayList;
import java.util.List;


public class UsersController
{
	private List<OnDataSourceChangeListener> dataSourceChangedListenrs = new ArrayList<OnDataSourceChangeListener>();
	IDataAcces dao;
	Context context;
	public UsersController(Context context) {
		dao = DAO.getInstance(context.getApplicationContext());
		this.context = context;
	}

	public boolean isLoggedIn() {
		SharedPreferences prefs = context.getSharedPreferences(AppConst.SharedPrefsName, Context.MODE_PRIVATE);
		if (prefs != null) {
			//If AppConst.SharedPrefs_IsLogin then it return true, the default is false
			return prefs.getBoolean(AppConst.SharedPrefs_IsLogin, false);
		}
		return false;
	}

	public List<User> GetUsersList()
	{
		return dao.GetUserList();
	}

	public User AddUser(String userName,String password,String phoneNumber)
	{
		User usr = new User();
		usr.setUserName(userName);
		usr.setPassword(password);
		usr.setPhoneNumber(phoneNumber);
		return dao.AddUser(usr);
	}

	public User GetUser(String userName,String password,String phoneNumber)
	{
		List<User> updatedList = dao.GetUserList();
		for (User t:updatedList)
		{
			if(t.getUserName() == userName && t.getPassword()==password && t.getPhoneNumber()==phoneNumber)
				return t;
		}
			return null;
	}

	public boolean isListEmpty()
	{
		try {
			List<User> updatedList = dao.GetUserList();
			if (updatedList.size() == 0)
				return true;
			else
				return false;
		}
		catch (Exception e)
		{return true;}
	}

	public void setLogedIn(User user)
	{
		if(user!=null)
		{
			//editor is builder - add data to db
			SharedPreferences prefs = context.getSharedPreferences(AppConst.SharedPrefsName, 0);
			if(prefs!=null)
			{
				Editor editor = prefs.edit();
				editor.putBoolean(AppConst.SharedPrefs_IsLogin, true);
				editor.putString(AppConst.SharedPrefs_UserName, user.getUserName());
				editor.commit();
				//in 1 line
				prefs.edit().putBoolean(AppConst.SharedPrefs_IsLogin, true).putString(AppConst.SharedPrefs_UserName, user.getUserName()).commit();
			}
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
}
