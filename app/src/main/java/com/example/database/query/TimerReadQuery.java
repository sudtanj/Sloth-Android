package com.example.database.query;

import com.example.database.dao.TimerDAO;
import com.example.database.data.Data;
import com.example.database.model.TimerModel;

import java.sql.SQLException;


public class TimerReadQuery extends Query
{
	private long mId;


	public TimerReadQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<TimerModel> processData() throws SQLException
	{
		Data<TimerModel> data = new Data<>();
		data.setDataObject(TimerDAO.read(mId));
		return data;
	}
}

