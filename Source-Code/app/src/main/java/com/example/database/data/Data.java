package com.example.database.data;

/**
 * Created by izzyengelbert on 12/3/2017.
 */

public class Data<T>
{
    private T mDataObject;


    public Data()
    {
    }


    public T getDataObject()
    {
        return mDataObject;
    }


    public void setDataObject(T dataObject)
    {
        mDataObject = dataObject;
    }
}