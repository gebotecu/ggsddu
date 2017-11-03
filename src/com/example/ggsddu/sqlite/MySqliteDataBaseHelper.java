package com.example.ggsddu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteDataBaseHelper extends SQLiteOpenHelper{

	private static final String TAG = "MySqliteDataBaseHelper";

	public MySqliteDataBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "onCreate");
//		String sql = "creat table if not exist person (_id integer primary key autoincrement,name vachar(96,0),age vachar(96,0)";
//		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
