package com.example.database.query;

import com.example.database.dao.TimerDAO;
import com.example.database.data.Data;
import com.example.database.model.TimerModel;

import java.sql.SQLException;
import java.util.List;


public class TimerReadAllQuery extends Query
{
	private long mSkip = -1l;
	private long mTake = -1l;


	public TimerReadAllQuery()
	{
	}


	public TimerReadAllQuery(long skip, long take)
	{
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<TimerModel>> processData() throws SQLException
	{
		Data<List<TimerModel>> data = new Data<>();
		data.setDataObject(TimerDAO.readAll(mSkip, mTake));
		return data;
	}
}
