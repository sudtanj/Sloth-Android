package com.example.database.model;


import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by izzyengelbert on 12/3/2017.
 */

@DatabaseTable(tableName="timer")
public class TimerModel {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TIMER_TYPE_ID = "timer_type_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_INFORMATION = "information";
    //public static final String COLUMN_IMAGE = "image";

    @DatabaseField(columnName=COLUMN_ID, generatedId=true) private long id;
    @DatabaseField(foreign=true, index=true) private TimerTypesModel timerType;
    @DatabaseField(columnName=COLUMN_NAME) private String name;
    @DatabaseField(columnName=COLUMN_INFORMATION) private String information;
    //@DatabaseField(columnName=COLUMN_IMAGE) private String image;
    @ForeignCollectionField
    private ForeignCollection<StepsModel> steps; // one to many


    // empty constructor
    public TimerModel()
    {
    }

    public TimerTypesModel getTimerType() {
        return timerType;
    }

    public void setTimerTypeId(TimerTypesModel timerType) {
        this.timerType = timerType;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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


    public List<StepsModel> getRecipes()
    {
        List<StepsModel> list = new ArrayList<>();
        for(StepsModel m : steps)
        {
            list.add(m);
        }
        return list;
    }


    public void setRecipes(ForeignCollection<StepsModel> steps)
    {
        this.steps = steps;
    }
}
