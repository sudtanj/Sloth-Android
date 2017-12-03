package com.example.database.query;


import com.example.database.dao.StepsDAO;
import com.example.database.data.Data;
import com.example.database.model.StepsModel;

import java.sql.SQLException;
import java.util.List;


public class StepsReadAllQuery extends Query
{
	private long mSkip = -1l;
	private long mTake = -1l;


	public StepsReadAllQuery()
	{
	}


	public StepsReadAllQuery(long skip, long take)
	{
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<StepsModel>> processData() throws SQLException
	{
		Data<List<StepsModel>> data = new Data<>();
		data.setDataObject(StepsDAO.readAll(mSkip, mTake));
		return data;
	}
}
