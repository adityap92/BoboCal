package com.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EventSQL {

	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_DATE = "_date";
	public static final String KEY_STIME = "start_time";
	public static final String KEY_ETIME = "end_time";
	public static final String KEY_COMMENTS = "_comments";
	
	private static final String DATABASE_NAME = "Eventsdb";
	public static final String DATABASE_TABLE = "eventsTable";
	public static final int DATABASE_VERSION = 1;
	
	
	private Dbhelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class Dbhelper extends SQLiteOpenHelper{	
		
		public Dbhelper(Context context) {
			super(context, DATABASE_NAME,null,DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + 
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_DATE + " TEXT NOT NULL, " + 
					KEY_STIME + " TEXT NOT NULL, " +
					KEY_ETIME + " TEXT NOT NULL, " +
					KEY_COMMENTS + " TEXT NOT NULL);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		onCreate(db);
		}
		
		
	}
	
	public EventSQL(Context c){
		ourContext=c;
	}
	
	public EventSQL open() throws SQLException{
		ourHelper = new Dbhelper(ourContext);
		ourDatabase= ourHelper.getWritableDatabase();
		
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String date, String sUpdate, String eUpdate,
			String descript) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_DATE,date);
		cv.put(KEY_STIME,sUpdate);
		cv.put(KEY_ETIME,eUpdate);
		cv.put(KEY_COMMENTS,descript);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
		
	}

	public String getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_DATE, KEY_STIME, KEY_ETIME, KEY_COMMENTS};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		//int iRow = c.getColumnIndex(KEY_ROWID);
		//int iRow = c.getColumnIndex(KEY_ROWID);
		//int iRow = c.getColumnIndex(KEY_ROWID);
		
		return result;
	}
	
}
