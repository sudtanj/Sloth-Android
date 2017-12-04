package com.example;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by izzyengelbert on 12/3/2017.
 */

public class SlothTimeApplication extends Application {

    private static SlothTimeApplication mInstance;

    private Tracker mTracker;

    public SlothTimeApplication(){
        mInstance = this;
    }


    @Override
    public void onCreate()
    {
        super.onCreate();

        // force AsyncTask to be initialized in the main thread due to the bug:
        // http://stackoverflow.com/questions/4280330/onpostexecute-not-being-called-in-asynctask-handler-runtime-exception
        try
        {
            Class.forName("android.os.AsyncTask");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        // init image caching
        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        cacheDir.mkdirs(); // requires android.permission.WRITE_EXTERNAL_STORAGE

        try
        {
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                    .threadPoolSize(3)
                    .threadPriority(Thread.NORM_PRIORITY - 2)
                    .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                    .diskCache(new LruDiscCache(cacheDir, new HashCodeFileNameGenerator(), 32 * 1024 * 1024))
                    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                    .build();

            ImageLoader.getInstance().init(config);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Context getContext()
    {
        return mInstance;
    }

    public synchronized Tracker getTracker()
    {
        if(mTracker==null)
        {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.setDryRun(!SlothTimeConfig.ANALYTICS);
            mTracker = analytics.newTracker(R.xml.analytics_app_tracker);
        }
        return mTracker;
    }
}
