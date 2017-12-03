package com.example.database.dao;

import com.example.database.DatabaseHelper;
import com.example.database.model.TimerTypesModel;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;


public class TimerTypesDAO extends DAO
{
	private static Dao<TimerTypesModel, Long> getDao() throws SQLException
	{
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
		return databaseHelper.getTimerTypesDAO();
	}
	
	
	public static int refresh(TimerTypesModel category) throws SQLException
	{
		Dao<TimerTypesModel, Long> dao = getDao();
		return dao.refresh(category);
	}
	
	
	public static int create(TimerTypesModel category) throws SQLException
	{
		Dao<TimerTypesModel, Long> dao = getDao();
		return dao.create(category);
	}
	
	
	public static TimerTypesModel read(long id) throws SQLException
	{
		Dao<TimerTypesModel, Long> dao = getDao();
		return dao.queryForId(id);
	}
	
	
	public static List<TimerTypesModel> readAll(long skip, long take) throws SQLException
	{
		Dao<TimerTypesModel, Long> dao = getDao();
		List<TimerTypesModel> list;
		if(skip==-1l && take==-1l)
		{
			QueryBuilder<TimerTypesModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(TimerTypesModel.COLUMN_ID, true);
			list = dao.query(queryBuilder.prepare());
		}
		else
		{
			QueryBuilder<TimerTypesModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(TimerTypesModel.COLUMN_ID, true);
			queryBuilder.offset(skip).limit(take);
			list = dao.query(queryBuilder.prepare());
		}
		return list;
	}
	
	
	public static int update(TimerTypesModel category) throws SQLException
	{
		Dao<TimerTypesModel, Long> dao = getDao();
		return dao.update(category);
	}
	
	
	public static int delete(long id) throws SQLException
	{
		Dao<TimerTypesModel, Long> dao = getDao();
		return dao.deleteById(id);
	}
	
	
	public static int deleteAll() throws SQLException
	{
		Dao<TimerTypesModel, Long> dao = getDao();
		DeleteBuilder<TimerTypesModel, Long> deleteBuilder = dao.deleteBuilder();
		return dao.delete(deleteBuilder.prepare());
	}
}
