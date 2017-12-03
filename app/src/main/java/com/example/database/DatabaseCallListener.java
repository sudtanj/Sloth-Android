package com.hciproject.makanapa.database;

import com.hciproject.makanapa.database.data.Data;


public interface DatabaseCallListener
{
	public void onDatabaseCallRespond(DatabaseCallTask task, Data<?> data);
	public void onDatabaseCallFail(DatabaseCallTask task, Exception exception);
}
