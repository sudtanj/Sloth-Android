package com.example.database.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;


@DatabaseTable(tableName="timer_types")
public class TimerTypesModel
{
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	//public static final String COLUMN_IMAGE = "image";

	@DatabaseField(columnName=COLUMN_ID, generatedId=true) private long id;
	@DatabaseField(columnName=COLUMN_NAME) private String name;
	//@DatabaseField(columnName=COLUMN_IMAGE) private String image;
	@ForeignCollectionField
    private ForeignCollection<TimerModel> timers; // one to many


	// empty constructor
	public TimerTypesModel()
	{
	}


	public long getId()
	{
		return id;
	}


	public void setId(long id)
	{
		this.id = id;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	/*public String getImage()
	{
		return image;
	}*/


	/*public void setImage(String image)
	{
		this.image = image;
	}*/


	public List<TimerModel> getTimers()
	{
		List<TimerModel> list = new ArrayList<>();
		for(TimerModel m : timers)
		{
			list.add(m);
		}
		return list;
	}


	public void setTimers(ForeignCollection<TimerModel> timers)
	{
		this.timers = timers;
	}
}
