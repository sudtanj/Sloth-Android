package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.IngredientDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.IngredientModel;

import java.sql.SQLException;
import java.util.List;


public class IngredientReadAllQuery extends Query
{
	private long mSkip = -1l;
	private long mTake = -1l;


	public IngredientReadAllQuery()
	{
	}


	public IngredientReadAllQuery(long skip, long take)
	{
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<IngredientModel>> processData() throws SQLException
	{
		Data<List<IngredientModel>> data = new Data<>();
		data.setDataObject(IngredientDAO.readAll(mSkip, mTake));
		return data;
	}
}
