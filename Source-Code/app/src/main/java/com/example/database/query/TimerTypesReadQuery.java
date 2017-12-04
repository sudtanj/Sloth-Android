package com.example.database.query;

import com.example.database.dao.TimerTypesDAO;
import com.example.database.data.Data;
import com.example.database.model.TimerTypesModel;
import java.sql.SQLException;


public class TimerTypesReadQuery extends Query
{
	private long mId;


	public TimerTypesReadQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<TimerTypesModel> processData() throws SQLException
	{
		Data<TimerTypesModel> data = new Data<>();
		data.setDataObject(TimerTypesDAO.read(mId));
		return data;
	}
}
