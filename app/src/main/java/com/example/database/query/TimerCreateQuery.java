package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.RecipeDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.RecipeModel;

import java.sql.SQLException;


public class 	RecipeCreateQuery extends Query
{
	private RecipeModel mRecipe;
	
	
	public RecipeCreateQuery(RecipeModel recipe)
	{
		mRecipe = recipe;
	}
	
	
	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(RecipeDAO.create(mRecipe));
		return data;
	}
}
