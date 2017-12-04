package com.example.database.query;


import com.example.database.dao.StepsDAO;
import com.example.database.data.Data;
import com.example.database.model.StepsModel;

import java.sql.SQLException;


public class StepsUpdateQuery extends Query
{
	private StepsModel mSteps;


	public StepsUpdateQuery(StepsModel steps)
	{
		mSteps = steps;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(StepsDAO.update(mSteps));
		return data;
	}
}
