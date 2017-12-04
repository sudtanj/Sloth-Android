package com.example.database.dao;


import com.example.database.DatabaseHelper;
import com.example.database.model.StepsModel;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;


public class StepsDAO extends DAO
{
	private static Dao<StepsModel, Long> getDao() throws SQLException
	{
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
		return databaseHelper.getStepsDAO();
	}
	
	
	public static int refresh(StepsModel stepsModel) throws SQLException
	{
		Dao<StepsModel, Long> dao = getDao();
		return dao.refresh(stepsModel);
	}
	
	
	public static int create(StepsModel stepsModel) throws SQLException
	{
		Dao<StepsModel, Long> dao = getDao();
		return dao.create(stepsModel);
	}
	
	
	public static StepsModel read(long id) throws SQLException
	{
		Dao<StepsModel, Long> dao = getDao();
		return dao.queryForId(id);
	}
	
	
	public static List<StepsModel> readAll(long skip, long take) throws SQLException
	{
		Dao<StepsModel, Long> dao = getDao();
		List<StepsModel> list;
		if(skip==-1l && take==-1l)
		{
			QueryBuilder<StepsModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(StepsModel.COLUMN_ID, true);
			list = dao.query(queryBuilder.prepare());
		}
		else
		{
			QueryBuilder<StepsModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.orderBy(StepsModel.COLUMN_ID, true);
			queryBuilder.offset(skip).limit(take);
			list = dao.query(queryBuilder.prepare());
		}
		return list;
	}


	public static List<StepsModel> readByTimer(long timerId, long skip, long take) throws SQLException
	{
		Dao<StepsModel, Long> dao = getDao();
		List<StepsModel> list;
		if(skip==-1l && take==-1l)
		{
			QueryBuilder<StepsModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.where().eq(StepsModel.COLUMN_TIMER_ID, timerId);
			queryBuilder.orderBy(StepsModel.COLUMN_ID, true);
			list = dao.query(queryBuilder.prepare());
		}
		else
		{
			QueryBuilder<StepsModel, Long> queryBuilder = dao.queryBuilder();
			queryBuilder.where().eq(StepsModel.COLUMN_TIMER_ID, timerId);
			queryBuilder.orderBy(StepsModel.COLUMN_ID, true);
			queryBuilder.offset(skip).limit(take);
			list = dao.query(queryBuilder.prepare());
		}
		return list;
	}
	
	
	public static int update(StepsModel stepsModel) throws SQLException
	{
		Dao<StepsModel, Long> dao = getDao();
		return dao.update(stepsModel);
	}
	
	
	public static int delete(long id) throws SQLException
	{
		Dao<StepsModel, Long> dao = getDao();
		return dao.deleteById(id);
	}
	
	
	public static int deleteAll() throws SQLException
	{
		Dao<StepsModel, Long> dao = getDao();
		DeleteBuilder<StepsModel, Long> deleteBuilder = dao.deleteBuilder();
		return dao.delete(deleteBuilder.prepare());
	}
}
