package com.example.database.query;

import com.example.database.dao.TimerDAO;
import com.example.database.data.Data;
import com.example.database.model.TimerModel;

import java.sql.SQLException;
import java.util.List;


public class TimerReadByTimerTypesQuery extends Query
{
	private long mTimerTypesId;
	private long mSkip = -1l;
	private long mTake = -1l;


	public TimerReadByTimerTypesQuery(long timerTypesId)
	{
		mTimerTypesId = timerTypesId;
	}


	public TimerReadByTimerTypesQuery(long categoryId, long skip, long take)
	{
		mTimerTypesId = categoryId;
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<TimerModel>> processData() throws SQLException
	{
		Data<List<TimerModel>> data = new Data<>();
		data.setDataObject(TimerDAO.readByTimerTypes(mTimerTypesId, mSkip, mTake));
		return data;
	}
}
