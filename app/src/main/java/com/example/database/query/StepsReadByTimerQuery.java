package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.IngredientDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.IngredientModel;

import java.sql.SQLException;
import java.util.List;


public class IngredientReadByRecipeQuery extends Query
{
	private long mRecipeId;
	private long mSkip = -1l;
	private long mTake = -1l;


	public IngredientReadByRecipeQuery(long recipeId)
	{
		mRecipeId = recipeId;
	}


	public IngredientReadByRecipeQuery(long recipeId, long skip, long take)
	{
		mRecipeId = recipeId;
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<IngredientModel>> processData() throws SQLException
	{
		Data<List<IngredientModel>> data = new Data<>();
		data.setDataObject(IngredientDAO.readByRecipe(mRecipeId, mSkip, mTake));
		return data;
	}
}
