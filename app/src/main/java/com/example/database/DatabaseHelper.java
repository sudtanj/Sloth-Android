package com.example.database;

import com.example.SlothTimeApplication;
import com.example.SlothTimeConfig;
import com.example.database.model.StepsModel;
import com.example.database.model.TimerModel;
import com.example.database.model.TimerTypesModel;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by izzyengelbert on 12/3/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = SlothTimeConfig.DATABASE_NAME;
    private static final String DATABASE_PATH = "/data/data/" + SlothTimeApplication.getContext().getPackageName() + "/databases/";
    private static final int DATABASE_VERSION = SlothTimeConfig.DATABASE_VERSION;
    private static final String PREFS_KEY_DATABASE_VERSION = "database_version";

    private Dao<TimerTypesModel, Long> mTimerTypesDAO = null;
    private Dao<TimerModel, Long> mTimerDAO = null;
    private Dao<StepsModel, Long> mStepsDAO = null;

    // singleton
    private static DatabaseHelper instance;
    public static synchronized DatabaseHelper getInstance()
    {
        if(instance==null) instance = new DatabaseHelper();
        return instance;
    }

    public DatabaseHelper(){
        super(SlothTimeApplication.getContext(), DATABASE_PATH + DATABASE_NAME, null, DATABASE_VERSION);
        if(!databaseExists() || DATABASE_VERSION>getVersion())
        {
            synchronized(this)
            {
                boolean success = copyPrepopulatedDatabase();
                if(success)
                {
                    setVersion(DATABASE_VERSION);
                }
            }
        }
    }

    private boolean databaseExists()
    {
        File file = new File(DATABASE_PATH + DATABASE_NAME);
        boolean exists = file.exists();
        Log.d("DatabaseHelper : " , "databaseExists()"+exists);
        return exists;
    }

    private boolean copyPrepopulatedDatabase()
    {
        // copy database from assets
        try
        {
            // create directories
            File dir = new File(DATABASE_PATH);
            dir.mkdirs();

            // output file name
            String outputFileName = DATABASE_PATH + DATABASE_NAME;
            Log.d("DatabaseHelper :" , "copyDatabase()" + outputFileName);

            // create streams
            InputStream inputStream = SlothTimeApplication.getContext().getAssets().open(DATABASE_NAME);
            OutputStream outputStream = new FileOutputStream(outputFileName);

            // write input to output
            byte[] buffer = new byte[1024];
            int length;
            while((length = inputStream.read(buffer))>0)
            {
                outputStream.write(buffer, 0, length);
            }

            // close streams
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void close()
    {
        super.close();
        mTimerTypesDAO = null;
        mTimerDAO = null;
        mStepsDAO = null;
    }

    public synchronized Dao<TimerTypesModel, Long> getTimerTypesDAO() throws java.sql.SQLException
    {
        if(mTimerTypesDAO==null)
        {
            mTimerTypesDAO = getDao(TimerTypesModel.class);
        }
        return mTimerTypesDAO;
    }
    public synchronized Dao<TimerModel, Long> getTimerDAO() throws java.sql.SQLException
    {
        if(mTimerDAO==null)
        {
            mTimerDAO = getDao(TimerModel.class);
        }
        return mTimerDAO;
    }
    public synchronized Dao<StepsModel, Long> getStepsDAO() throws java.sql.SQLException
    {
        if(mStepsDAO==null)
        {
            mStepsDAO = getDao(StepsModel.class);
        }
        return mStepsDAO;
    }

    private int getVersion()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SlothTimeApplication.getContext());
        return sharedPreferences.getInt(PREFS_KEY_DATABASE_VERSION, 0);
    }


    private void setVersion(int version)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(SlothTimeApplication.getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFS_KEY_DATABASE_VERSION, version);
        editor.commit();
    }
}
