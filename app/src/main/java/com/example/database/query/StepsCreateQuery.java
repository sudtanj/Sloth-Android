package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.IngredientDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.IngredientModel;

import java.sql.SQLException;


public class IngredientCreateQuery extends Query
{
	private IngredientModel mIngredient;


	public IngredientCreateQuery(IngredientModel ingredient)
	{
		mIngredient = ingredient;
	}
	
	
	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(IngredientDAO.create(mIngredient));
		return data;
	}
}
