package com.example.ggsddu.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MyDataBaseManager {
	private static final String TAG = "MyDataBaseManager";
	private static MyDataBaseManager mMyDataBaseManager;
	private MySqliteDataBaseHelper mMySqliteDataBaseHelper;
	private SQLiteDatabase mSqLiteDatabase;
	public MyDataBaseManager(){
		
	}
	
	public static MyDataBaseManager getInstance(){
		if(mMyDataBaseManager==null){
			mMyDataBaseManager = new MyDataBaseManager();
		}
		return mMyDataBaseManager;
	}
	
	public void initDataBase(Context context,String name,CursorFactory factory,int version){
		if(mMySqliteDataBaseHelper==null){
			
			mMySqliteDataBaseHelper = new MySqliteDataBaseHelper(context, name, factory, version);
		}
		mSqLiteDatabase = mMySqliteDataBaseHelper.getWritableDatabase();
	}
	
	public SQLiteDatabase getDataBase(){
		if(mSqLiteDatabase==null){
			mSqLiteDatabase = mMySqliteDataBaseHelper.getWritableDatabase();
		}
		return mSqLiteDatabase;
	}
	public void exeSql(String sql){
		Log.i(TAG, "sql="+sql);
		if(mSqLiteDatabase!=null){
			mSqLiteDatabase.execSQL(sql);
//			mSqLiteDatabase.close();
		}
	}
	
	public Cursor rawQuery(String sql,String[] selectionArgs){
		Cursor cursor = null;
		Log.i(TAG, "sql="+sql+"---selectionArgs="+selectionArgs);
		if(mSqLiteDatabase!=null){
			cursor = mSqLiteDatabase.rawQuery(sql, selectionArgs);
//			mSqLiteDatabase.close();
		}
		return cursor;
	}
	
	public Cursor query(String table, String[]columns, String selection, String[]selectionArgs, String groupBy,String having,String orderBy){
		Cursor cursor = null;
		if(mSqLiteDatabase!=null){
			cursor = mSqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
//			mSqLiteDatabase.close();
		}
		return cursor;
	}
	
	//²åÈë
	public long insert(String table,String nullColumnHack,ContentValues values){
		long result = 0;
		if(mSqLiteDatabase!=null){
			result = mSqLiteDatabase.insert(table, nullColumnHack, values);
		}
		return result;
	}
	
	//¸üÐÂ
	public int update(String table,ContentValues values,String whereClause,String[] whereArgs){
		int result = 0;
		if(mSqLiteDatabase!=null){
			result = mSqLiteDatabase.update(table, values, whereClause, whereArgs);
		}
		return result;
	}
	
	//É¾³ý
	public int delete(String table,String whereClause,String[] whereArgs){
		int result = 0;
		if(mSqLiteDatabase!=null){
			result = mSqLiteDatabase.delete(table, whereClause, whereArgs);
		}
		return result;
	}
}
