package com.tap.sportplay.dao.sql;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tap.sportplay.dao.IGenericDao;


public abstract class AbstractSqlDao<T> implements IGenericDao<T>{

	private BaseSqlDao dbInstance;
	private String tableName;
	
	protected String getTableName(){
		return tableName;
	}
	

	public AbstractSqlDao(BaseSqlDao database, String tableName) {
		this.setDbInstance(database);
		this.tableName = tableName;
	}

	public BaseSqlDao getDbInstance() {
		return dbInstance;
	}

	public void setDbInstance(BaseSqlDao dbInstance) {
		this.dbInstance = dbInstance;
	}
	
	protected abstract ContentValues ConvertObjectToContentValues(T entry);
	protected abstract String [] getFields();
	protected abstract T ConvertCursorToObject(Cursor c);

	
	protected int getCursorInt(Cursor c, String columnName){
		
		int index = c.getColumnIndex(columnName);
		if (index >= 0) {
			return c.getInt(index);
		}		
		return 0;		
	}
	protected String getCursorString(Cursor c, String columnName){
		
		int index = c.getColumnIndex(columnName);
		if (index >= 0) {
			return c.getString(index);
		}		
		return null;		
	}
	protected List<T> getList(Cursor c){

		List<T> list = new ArrayList<T>();
		c.moveToPosition(-1);
		
		while (c.moveToNext()){
			T entry = ConvertCursorToObject(c);
			list.add(entry);
		}
		
		return list;
	}
	
	@Override
	public long Insert(T entity) {

		SQLiteDatabase db = this.dbInstance.getWritableDatabase();
				
		ContentValues values = ConvertObjectToContentValues(entity);
		
		long newRowId = db.insert(
				this.tableName, DBConstants.COLUMN_NAME_NULLABLE, values);
		
		return newRowId;
	}


	@Override
	public T Load(int id, String [] fields) {
		
		SQLiteDatabase db = this.dbInstance.getReadableDatabase();
		
		if (fields == null || fields.length == 0)
			fields = getFields();
		
		Cursor c = db.query(
			    this.tableName,  						  // The table to query
			    fields,                               	  // The columns to return
			    DBConstants.KEY_ID + "=" + id,            // The columns for the WHERE clause
			    null,                            		  // The values for the WHERE clause
			    null,                                     // don't group the rows
			    null,                                     // don't filter by row groups
			    null                                      // The sort order
			    );
		
		if (c != null)
			c.moveToFirst();
				
		return ConvertCursorToObject(c);
	}


	@Override
	public int Update(T entity) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int Delete(T entity) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<T> GetAll(String [] fields, String where) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long Count() {
		// TODO Auto-generated method stub
		return 0;
	}



}
