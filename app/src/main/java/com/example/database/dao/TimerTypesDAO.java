package com.example.database.dao;

import com.hciproject.makanapa.database.DatabaseHelper;
import com.hciproject.makanapa.database.model.CategoryModel;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;


public class CategoryDAO extends DAO
{
	private static Dao<CategoryModel, Long> getDao() throws SQLException
	{
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
		return databaseHelper.getCategoryDao();
	}
	
	
	public static int refresh(CategoryModel category) throws SQLException
	{
		Dao<CategoryModel, Long> dao = getDao();
		return dao.refresh(category);
	}
	
	
	public static int create(CategoryModel category) throws SQLException
	{
		Dao<CategoryModel, Long> dao = getDao();
		return dao.create(category);
	}
	
	
	public static CategoryModel read(long id) throws SQLException
	{
		Dao<CategoryModel, Long> dao = getDao();
		return dao.queryForId(id);
	}
	
	
	public static List<CategoryModel> readAll(long skip, long take) throws SQLException
	{
		Dao<CategoryModel, Long> dao = getDao();
		List<CategoryModel> list;
		if(skip==-1l && take==-1l)
		{
			QueryBuilder<CategoryModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(CategoryModel.COLUMN_ID, true);
			list = dao.query(queryBuilder.prepare());
		}
		else
		{
			QueryBuilder<CategoryModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(CategoryModel.COLUMN_ID, true);
			queryBuilder.offset(skip).limit(take);
			list = dao.query(queryBuilder.prepare());
		}
		return list;
	}
	
	
	public static int update(CategoryModel category) throws SQLException
	{
		Dao<CategoryModel, Long> dao = getDao();
		return dao.update(category);
	}
	
	
	public static int delete(long id) throws SQLException
	{
		Dao<CategoryModel, Long> dao = getDao();
		return dao.deleteById(id);
	}
	
	
	public static int deleteAll() throws SQLException
	{
		Dao<CategoryModel, Long> dao = getDao();
		DeleteBuilder<CategoryModel, Long> deleteBuilder = dao.deleteBuilder();
		return dao.delete(deleteBuilder.prepare());
	}
}
