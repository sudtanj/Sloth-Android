package com.example;

import android.app.Application;
import android.content.Context;

/**
 * Created by izzyengelbert on 12/3/2017.
 */

public class SlothTimeApplication extends Application {

    private static SlothTimeApplication mInstance;

    public SlothTimeApplication(){
        mInstance = this;
    }


    public static Context getContext()
    {
        return mInstance;
    }
}
