package com.example.database.query;

import com.example.database.dao.TimerTypesDAO;
import com.example.database.data.Data;

import java.sql.SQLException;


public class TimerTypesDeleteQuery extends Query
{
	private long mId;


	public TimerTypesDeleteQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(TimerTypesDAO.delete(mId));
		return data;
	}
}
