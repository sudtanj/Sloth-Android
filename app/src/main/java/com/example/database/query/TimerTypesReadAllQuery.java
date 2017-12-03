package com.example.database.query;

import com.example.database.dao.TimerTypesDAO;
import com.example.database.data.Data;
import com.example.database.model.TimerTypesModel;

import java.sql.SQLException;
import java.util.List;


public class TimerTypesReadAllQuery extends Query
{
	private long mSkip = -1l;
	private long mTake = -1l;


	public TimerTypesReadAllQuery()
	{
	}


	public TimerTypesReadAllQuery(long skip, long take)
	{
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<TimerTypesModel>> processData() throws SQLException
	{
		Data<List<TimerTypesModel>> data = new Data<>();
		data.setDataObject(TimerTypesDAO.readAll(mSkip, mTake));
		return data;
	}
}
