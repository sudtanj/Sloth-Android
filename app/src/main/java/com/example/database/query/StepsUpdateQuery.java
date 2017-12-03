package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.IngredientDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.IngredientModel;

import java.sql.SQLException;


public class IngredientUpdateQuery extends Query
{
	private IngredientModel mIngredient;


	public IngredientUpdateQuery(IngredientModel ingredient)
	{
		mIngredient = ingredient;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(IngredientDAO.update(mIngredient));
		return data;
	}
}
