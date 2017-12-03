package com.example.database.query;

import com.example.database.dao.TimerTypesDAO;
import com.example.database.data.Data;
import com.example.database.model.TimerTypesModel;

import java.sql.SQLException;


public class TimerTypesUpdateQuery extends Query
{
	private TimerTypesModel mTimerTypes;


	public TimerTypesUpdateQuery(TimerTypesModel timerTypes)
	{
		mTimerTypes = timerTypes;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(TimerTypesDAO.update(mTimerTypes));
		return data;
	}
}
