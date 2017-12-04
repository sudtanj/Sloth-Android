package com.example.database.query;

import com.example.database.dao.TimerTypesDAO;
import com.example.database.data.Data;
import com.example.database.model.TimerTypesModel;

import java.sql.SQLException;


public class TimerTypesCreateQuery extends Query
{
	private TimerTypesModel mTimerTypes;


	public TimerTypesCreateQuery(TimerTypesModel timerTypes)
	{
		mTimerTypes = timerTypes;
	}
	
	
	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(TimerTypesDAO.create(mTimerTypes));
		return data;
	}
}
