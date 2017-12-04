package com.example.database.query;

import com.example.database.dao.StepsDAO;
import com.example.database.data.Data;

import java.sql.SQLException;


public class StepsDeleteAllQuery extends Query
{
	public StepsDeleteAllQuery()
	{
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(StepsDAO.deleteAll());
		return data;
	}
}
