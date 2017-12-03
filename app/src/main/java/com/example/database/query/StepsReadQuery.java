package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.IngredientDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.IngredientModel;

import java.sql.SQLException;


public class IngredientReadQuery extends Query
{
	private long mId;


	public IngredientReadQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<IngredientModel> processData() throws SQLException
	{
		Data<IngredientModel> data = new Data<>();
		data.setDataObject(IngredientDAO.read(mId));
		return data;
	}
}
