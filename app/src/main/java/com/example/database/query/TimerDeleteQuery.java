package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.RecipeDAO;
import com.hciproject.makanapa.database.data.Data;

import java.sql.SQLException;


public class RecipeDeleteQuery extends Query
{
	private long mId;


	public RecipeDeleteQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(RecipeDAO.delete(mId));
		return data;
	}
}
