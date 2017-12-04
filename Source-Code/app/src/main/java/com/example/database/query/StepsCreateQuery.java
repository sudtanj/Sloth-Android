package com.example.database.query;

import com.example.database.dao.StepsDAO;
import com.example.database.data.Data;
import com.example.database.model.StepsModel;

import java.sql.SQLException;


public class StepsCreateQuery extends Query
{
	private StepsModel mSteps;


	public StepsCreateQuery(StepsModel steps)
	{
		mSteps = steps;
	}
	
	
	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(StepsDAO.create(mSteps));
		return data;
	}
}
