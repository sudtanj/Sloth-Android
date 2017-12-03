package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.CategoryDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.CategoryModel;

import java.sql.SQLException;
import java.util.List;


public class CategoryReadAllQuery extends Query
{
	private long mSkip = -1l;
	private long mTake = -1l;


	public CategoryReadAllQuery()
	{
	}


	public CategoryReadAllQuery(long skip, long take)
	{
		mSkip = skip;
		mTake = take;
	}


	@Override
	public Data<List<CategoryModel>> processData() throws SQLException
	{
		Data<List<CategoryModel>> data = new Data<>();
		data.setDataObject(CategoryDAO.readAll(mSkip, mTake));
		return data;
	}
}
