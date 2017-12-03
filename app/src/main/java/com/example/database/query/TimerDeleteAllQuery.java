package com.example.database.query;

import com.example.database.dao.TimerDAO;
import com.example.database.data.Data;

import java.sql.SQLException;


public class TimerDeleteAllQuery extends Query
{
	public TimerDeleteAllQuery()
	{
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(TimerDAO.deleteAll());
		return data;
	}
}
