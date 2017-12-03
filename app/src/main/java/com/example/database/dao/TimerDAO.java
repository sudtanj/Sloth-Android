package com.example.database.dao;


import com.example.database.DatabaseHelper;
import com.example.database.model.TimerModel;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;


public class TimerDAO extends DAO
{
	private static Dao<TimerModel, Long> getDao() throws SQLException
	{
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
		return databaseHelper.getTimerDAO();
	}
	
	
	public static int refresh(TimerModel recipe) throws SQLException
	{
		Dao<TimerModel, Long> dao = getDao();
		return dao.refresh(recipe);
	}
	
	
	public static int create(TimerModel recipe) throws SQLException
	{
		Dao<TimerModel, Long> dao = getDao();
		return dao.create(recipe);
	}
	
	
	public static TimerModel read(long id) throws SQLException
	{
		Dao<TimerModel, Long> dao = getDao();
		return dao.queryForId(id);
	}
	
	
	public static List<TimerModel> readAll(long skip, long take) throws SQLException
	{
		Dao<TimerModel, Long> dao = getDao();
		List<TimerModel> list;
		if(skip==-1l && take==-1l)
		{
			QueryBuilder<TimerModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(TimerModel.COLUMN_NAME, true);
			list = dao.query(queryBuilder.prepare());
		}
		else
		{
			QueryBuilder<TimerModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(TimerModel.COLUMN_NAME, true);
			queryBuilder.offset(skip).limit(take);
			list = dao.query(queryBuilder.prepare());
		}
		return list;
	}


	public static List<TimerModel> readByTimerTypes(long timerTypeId, long skip, long take) throws SQLException
	{
		Dao<TimerModel, Long> dao = getDao();
		List<TimerModel> list;
		if(skip==-1l && take==-1l)
		{
			QueryBuilder<TimerModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.where().eq(TimerModel.COLUMN_TIMER_TYPE_ID, timerTypeId);
			queryBuilder.orderBy(TimerModel.COLUMN_NAME, true);
			list = dao.query(queryBuilder.prepare());
		}
		else
		{
			QueryBuilder<TimerModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.where().eq(TimerModel.COLUMN_TIMER_TYPE_ID, timerTypeId);
			queryBuilder.orderBy(TimerModel.COLUMN_NAME, true);
			queryBuilder.offset(skip).limit(take);
			list = dao.query(queryBuilder.prepare());
		}
		return list;
	}
	
	
	public static int update(TimerModel recipe) throws SQLException
	{
		Dao<TimerModel, Long> dao = getDao();
		return dao.update(recipe);
	}
	
	
	public static int delete(long id) throws SQLException
	{
		Dao<TimerModel, Long> dao = getDao();
		return dao.deleteById(id);
	}
	
	
	public static int deleteAll() throws SQLException
	{
		Dao<TimerModel, Long> dao = getDao();
		DeleteBuilder<TimerModel, Long> deleteBuilder = dao.deleteBuilder();
		return dao.delete(deleteBuilder.prepare());
	}

}
