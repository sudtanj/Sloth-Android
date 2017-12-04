package com.example.database.query;

import com.example.database.dao.StepsDAO;
import com.example.database.data.Data;
import com.example.database.model.StepsModel;

import java.sql.SQLException;
import java.util.List;


public class StepsReadByTimerQuery extends Query
{
	private long mRecipeId;
	private long mSkip = -1l;
	private long mTake = -1l;


	public StepsReadByTimerQuery(long recipeId)
	{
		mRecipeId = recipeId;
	}


	public StepsReadByTimerQuery(long recipeId, long skip, long take)
	{
		mRecipeId = recipeId;
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<StepsModel>> processData() throws SQLException
	{
		Data<List<StepsModel>> data = new Data<>();
		data.setDataObject(StepsDAO.readByTimer(mRecipeId, mSkip, mTake));
		return data;
	}
}
