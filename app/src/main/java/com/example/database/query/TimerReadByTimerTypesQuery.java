package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.RecipeDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.RecipeModel;

import java.sql.SQLException;
import java.util.List;


public class RecipeReadByCategoryQuery extends Query
{
	private long mCategoryId;
	private long mSkip = -1l;
	private long mTake = -1l;


	public RecipeReadByCategoryQuery(long categoryId)
	{
		mCategoryId = categoryId;
	}


	public RecipeReadByCategoryQuery(long categoryId, long skip, long take)
	{
		mCategoryId = categoryId;
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<RecipeModel>> processData() throws SQLException
	{
		Data<List<RecipeModel>> data = new Data<>();
		data.setDataObject(RecipeDAO.readByCategory(mCategoryId, mSkip, mTake));
		return data;
	}
}
