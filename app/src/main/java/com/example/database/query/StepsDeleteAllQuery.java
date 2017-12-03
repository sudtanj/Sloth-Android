package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.IngredientDAO;
import com.hciproject.makanapa.database.data.Data;

import java.sql.SQLException;


public class IngredientDeleteAllQuery extends Query
{
	public IngredientDeleteAllQuery()
	{
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(IngredientDAO.deleteAll());
		return data;
	}
}
