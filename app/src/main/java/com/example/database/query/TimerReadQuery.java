package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.RecipeDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.RecipeModel;

import java.sql.SQLException;


public class RecipeReadQuery extends Query
{
	private long mId;


	public RecipeReadQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<RecipeModel> processData() throws SQLException
	{
		Data<RecipeModel> data = new Data<>();
		data.setDataObject(RecipeDAO.read(mId));
		return data;
	}
}
