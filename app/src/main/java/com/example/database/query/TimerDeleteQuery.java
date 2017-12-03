package com.example.database.query;

import com.example.database.dao.TimerDAO;
import com.example.database.data.Data;

import java.sql.SQLException;


public class TimerDeleteQuery extends Query
{
	private long mId;


	public TimerDeleteQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(TimerDAO.delete(mId));
		return data;
	}
}
