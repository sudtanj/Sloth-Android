package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.RecipeDAO;
import com.hciproject.makanapa.database.data.Data;

import java.sql.SQLException;


public class RecipeDeleteAllQuery extends Query
{
	public RecipeDeleteAllQuery()
	{
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(RecipeDAO.deleteAll());
		return data;
	}
}
