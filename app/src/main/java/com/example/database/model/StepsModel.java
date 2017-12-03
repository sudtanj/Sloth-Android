package com.example.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Timer;

/**
 * Created by izzyengelbert on 12/3/2017.
 */

@DatabaseTable(tableName="steps")
public class StepsModel {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TIMER_ID = "timer_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIME = "time";

    @DatabaseField(columnName=COLUMN_ID, generatedId=true) private long id;
    //@DatabaseField( foreign=true, index=true) private Timer timer;
    @DatabaseField( foreign=true, index=true) private TimerModel timerModel;
    @DatabaseField(columnName=COLUMN_NAME) private String name;
    @DatabaseField(columnName=COLUMN_TIME) private int time;


    // empty constructor
    public StepsModel()
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


    public Timer getTimer()
    {
        //return timer;
        return null;
    }


    public void setTimer(Timer recipe)
    {
        //this.timer = timer;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public int getTime()
    {
        return time;
    }


    public void setTime(int quantity)
    {
        this.time = time;
    }
}
