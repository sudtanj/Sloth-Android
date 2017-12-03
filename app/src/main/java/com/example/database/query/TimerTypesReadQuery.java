package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.CategoryDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.CategoryModel;

import java.sql.SQLException;


public class CategoryReadQuery extends Query
{
	private long mId;


	public CategoryReadQuery(long id)
	{
		mId = id;
	}


	@Override
	public Data<CategoryModel> processData() throws SQLException
	{
		Data<CategoryModel> data = new Data<>();
		data.setDataObject(CategoryDAO.read(mId));
		return data;
	}
}
