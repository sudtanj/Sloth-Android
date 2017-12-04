package com.example.database.query;


import com.example.database.dao.StepsDAO;
import com.example.database.data.Data;
import com.example.database.model.StepsModel;

import java.sql.SQLException;


public class StepsReadQuery extends Query
{
	private long mId;


	public StepsReadQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<StepsModel> processData() throws SQLException
	{
		Data<StepsModel> data = new Data<>();
		data.setDataObject(StepsDAO.read(mId));
		return data;
	}
}
