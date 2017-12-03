package com.example.database.query;


import com.example.database.dao.TimerTypesDAO;
import com.example.database.data.Data;

import java.sql.SQLException;


public class TimerTypesDeleteAllQuery extends Query
{
	public TimerTypesDeleteAllQuery()
	{
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(TimerTypesDAO.deleteAll());
		return data;
	}
}
