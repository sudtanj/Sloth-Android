package com.hciproject.makanapa.database.query;

import com.hciproject.makanapa.database.dao.CategoryDAO;
import com.hciproject.makanapa.database.data.Data;
import com.hciproject.makanapa.database.model.CategoryModel;

import java.sql.SQLException;


public class CategoryUpdateQuery extends Query
{
	private CategoryModel mCategory;


	public CategoryUpdateQuery(CategoryModel category)
	{
		mCategory = category;
	}


	@Override
	public Data<Integer> processData() throws SQLException
	{
		Data<Integer> data = new Data<>();
		data.setDataObject(CategoryDAO.update(mCategory));
		return data;
	}
}
