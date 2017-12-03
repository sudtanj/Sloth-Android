package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.CategoryDAO;
import com.hciproject.makanapa.database.data.Data;

import java.sql.SQLException;


public class CategoryDeleteQuery extends Query
{
	private long mId;


	public CategoryDeleteQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(CategoryDAO.delete(mId));
		return data;
	}
}
