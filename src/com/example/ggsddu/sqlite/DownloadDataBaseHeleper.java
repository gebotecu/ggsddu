package com.example.ggsddu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DownloadDataBaseHeleper extends SQLiteOpenHelper {
	
	private static final String NAME = "downloadDB";
	private static final int VERSION = 1;
	public DownloadDataBaseHeleper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, NAME, factory, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
