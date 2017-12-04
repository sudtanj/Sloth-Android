package com.example.database.query;


import com.example.database.dao.StepsDAO;
import com.example.database.data.Data;

import java.sql.SQLException;


public class StepsDeleteQuery extends Query
{
	private long mId;


	public StepsDeleteQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(StepsDAO.delete(mId));
		return data;
	}
}
