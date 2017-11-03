package com.example.ggsddu;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ggsddu.sqlite.MyDataBaseManager;
import com.example.ggsddu.utils.Constans;

public class SqliteTestActivity extends Activity implements OnClickListener {

	private static final String TAG = "SqliteTestActivity";
	private Button mCreateTableButton;
	private Button mInsertDataButton;
	private Button mUpdataDataButton;
	private Button mDeleteDataButton;
	private Button mQueryDataButton;

	private ListView mListView;
	private Cursor mCursor;
	private MySqliteListViewAdapter mListViewAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlit_test_activity);
		mCreateTableButton = (Button) findViewById(R.id.create_table);
		mInsertDataButton = (Button) findViewById(R.id.insert_data);
		mUpdataDataButton = (Button) findViewById(R.id.updata_data);
		mDeleteDataButton = (Button) findViewById(R.id.delete_data);
		mQueryDataButton = (Button) findViewById(R.id.query_data);

		mCreateTableButton.setOnClickListener(this);
		mInsertDataButton.setOnClickListener(this);
		mUpdataDataButton.setOnClickListener(this);
		mDeleteDataButton.setOnClickListener(this);
		mQueryDataButton.setOnClickListener(this);
		
		MyDataBaseManager.getInstance().initDataBase(this, "ggsddu", null, Constans.PERSON_TABLE_VERSION);
	
		mListView = (ListView) findViewById(R.id.sqlite_test_listview);
		
	}

	class MySqliteListViewAdapter extends CursorAdapter{
		
		public MySqliteListViewAdapter(Context context, Cursor c, int flags) {
			super(context, c, flags);
			Log.i(TAG, "MySqliteListViewAdapter");
		}

		@Override
		public void bindView(View arg0, Context arg1, Cursor arg2) {
			Log.i(TAG, "MySqliteListViewAdapter---bindView");
			TextView textView = (TextView) arg0.findViewById(R.id.viewholder_test_item_textview);
			textView.setText(arg2.getString(arg2.getColumnIndex(Constans.PERSON_NAME)));
		}

		@Override
		public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
			Log.i(TAG, "MySqliteListViewAdapter---newView");
			View view = LayoutInflater.from(arg0).inflate(R.layout.viewholder_test_item, null);
			return view;
		}
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.create_table:
			String createTableSql = "create table if not exists "+Constans.PERSON_TABLE_NAME+" (_id integer primary key autoincrement,"+Constans.PERSON_NAME+" varchar(96,0),"+Constans.PERSON_AGE+" int(32,0) default 0);";
			MyDataBaseManager.getInstance().exeSql(createTableSql);
			
			break;
		case R.id.insert_data:
			//�������
//			SQLiteDatabase sqLiteDatabase = MyDataBaseManager.getInstance().getDataBase();
//			Log.i(TAG, "currentTime="+System.currentTimeMillis());
//			sqLiteDatabase.beginTransaction();
//			for(int i=0;i<100;i++){
//				
//				String insertDataSql = "insert into "+Constans.PERSON_TABLE_NAME+" values (null,'С��"+i+"',"+i+");";
//				sqLiteDatabase.execSQL(insertDataSql);
//			}
//			Log.i(TAG, "currentTime="+System.currentTimeMillis());
//			sqLiteDatabase.setTransactionSuccessful();
//			sqLiteDatabase.endTransaction();
			//sql������
			Log.i(TAG, "currentTime="+System.currentTimeMillis());
			for(int i=0;i<100;i++){
				
				String insertDataSql = "insert into "+Constans.PERSON_TABLE_NAME+" values (null,'С��"+i+"',"+i+")";
				MyDataBaseManager.getInstance().exeSql(insertDataSql);
			}
			Log.i(TAG, "currentTime="+System.currentTimeMillis());
			mCursor = MyDataBaseManager.getInstance().query(Constans.PERSON_TABLE_NAME, null, null, null, null, null, null);
			mListViewAdapter = new MySqliteListViewAdapter(this, mCursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
			mListView.setAdapter(mListViewAdapter);
			//api����
//			ContentValues contentValues = new ContentValues();
//			contentValues.put(Constans.PERSON_NAME, "С��");
//			contentValues.put(Constans.PERSON_AGE, 20);
//			long insertResult = MyDataBaseManager.getInstance().insert(Constans.PERSON_TABLE_NAME, null, contentValues);
//			Log.i(TAG, "insertResult="+insertResult);
			break;
		case R.id.updata_data:
			//sql������
//			String updataSqlString = "update "+Constans.PERSON_TABLE_NAME+" set "+Constans.PERSON_NAME+"='��ë' where "+Constans.PERSON_AGE+"=10;";
//			MyDataBaseManager.getInstance().exeSql(updataSqlString);
			
			//api����
			ContentValues contentValues = new ContentValues();
			contentValues.put(Constans.PERSON_NAME,"����");
			int updataResult = MyDataBaseManager.getInstance().update(Constans.PERSON_TABLE_NAME, contentValues, Constans.PERSON_AGE+" = ?", new String[]{"3"});
			Log.i(TAG, "updataResult="+updataResult);
			refreshListView();
			break;
		case R.id.delete_data:
			//sql���ɾ��
//			String deleteDataSql = "delete from "+Constans.PERSON_TABLE_NAME+" where "+Constans.PERSON_AGE+"=25;";
//			MyDataBaseManager.getInstance().exeSql(deleteDataSql);
			
			//apiɾ��
			int deleteResult = MyDataBaseManager.getInstance().delete(Constans.PERSON_TABLE_NAME, Constans.PERSON_AGE+"=?", new String[]{"15"});
			Log.i(TAG, "deleteResult="+deleteResult);
			refreshListView();
			break;
		case R.id.query_data:
			//sql����ѯ
			String queryDataSql = "select * from "+Constans.PERSON_TABLE_NAME+" where "+Constans.PERSON_AGE+">30 and "+Constans.PERSON_AGE+"<40";
//			Cursor mCursor = MyDataBaseManager.getInstance().rawQuery(queryDataSql, null);
			mCursor = MyDataBaseManager.getInstance().query(Constans.PERSON_TABLE_NAME, null, Constans.PERSON_AGE+">?", new String[]{"30"}, null, null, null);
			while(mCursor.moveToNext()){
				String personNameString = mCursor.getString(mCursor.getColumnIndex(Constans.PERSON_NAME));
				int personAge = mCursor.getInt(mCursor.getColumnIndex(Constans.PERSON_AGE));
				Log.i(TAG, "personNameString="+personNameString+"---personAge="+personAge);
			}
			Log.i(TAG, "mCursor.getCount()="+mCursor.getCount());
			break;
		
		default:
			break;
		}

	}
	private void refreshListView() {
		mCursor = MyDataBaseManager.getInstance().query(Constans.PERSON_TABLE_NAME, null, null, null, null, null, null);
		if(mListViewAdapter!=null){
			mListViewAdapter.notifyDataSetChanged();
		}
	}
}
