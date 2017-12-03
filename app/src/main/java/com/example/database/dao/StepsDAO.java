package com.hciproject.makanapa.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.hciproject.makanapa.database.DatabaseHelper;
import com.hciproject.makanapa.database.model.IngredientModel;

import java.sql.SQLException;
import java.util.List;


public class IngredientDAO extends DAO
{
	private static Dao<IngredientModel, Long> getDao() throws SQLException
	{
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
		return databaseHelper.getIngredientDao();
	}
	
	
	public static int refresh(IngredientModel ingredient) throws SQLException
	{
		Dao<IngredientModel, Long> dao = getDao();
		return dao.refresh(ingredient);
	}
	
	
	public static int create(IngredientModel ingredient) throws SQLException
	{
		Dao<IngredientModel, Long> dao = getDao();
		return dao.create(ingredient);
	}
	
	
	public static IngredientModel read(long id) throws SQLException
	{
		Dao<IngredientModel, Long> dao = getDao();
		return dao.queryForId(id);
	}
	
	
	public static List<IngredientModel> readAll(long skip, long take) throws SQLException
	{
		Dao<IngredientModel, Long> dao = getDao();
		List<IngredientModel> list;
		if(skip==-1l && take==-1l)
		{
			QueryBuilder<IngredientModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(IngredientModel.COLUMN_ID, true);
			list = dao.query(queryBuilder.prepare());
		}
		else
		{
			QueryBuilder<IngredientModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(IngredientModel.COLUMN_ID, true);
			queryBuilder.offset(skip).limit(take);
			list = dao.query(queryBuilder.prepare());
		}
		return list;
	}


	public static List<IngredientModel> readByRecipe(long recipeId, long skip, long take) throws SQLException
	{
		Dao<IngredientModel, Long> dao = getDao();
		List<IngredientModel> list;
		if(skip==-1l && take==-1l)
		{
			QueryBuilder<IngredientModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.where().eq(IngredientModel.COLUMN_RECIPE_ID, recipeId);
			queryBuilder.orderBy(IngredientModel.COLUMN_ID, true);
			list = dao.query(queryBuilder.prepare());
		}
		else
		{
			QueryBuilder<IngredientModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.where().eq(IngredientModel.COLUMN_RECIPE_ID, recipeId);
			queryBuilder.orderBy(IngredientModel.COLUMN_ID, true);
			queryBuilder.offset(skip).limit(take);
			list = dao.query(queryBuilder.prepare());
		}
		return list;
	}
	
	
	public static int update(IngredientModel ingredient) throws SQLException
	{
		Dao<IngredientModel, Long> dao = getDao();
		return dao.update(ingredient);
	}
	
	
	public static int delete(long id) throws SQLException
	{
		Dao<IngredientModel, Long> dao = getDao();
		return dao.deleteById(id);
	}
	
	
	public static int deleteAll() throws SQLException
	{
		Dao<IngredientModel, Long> dao = getDao();
		DeleteBuilder<IngredientModel, Long> deleteBuilder = dao.deleteBuilder();
		return dao.delete(deleteBuilder.prepare());
	}
}
