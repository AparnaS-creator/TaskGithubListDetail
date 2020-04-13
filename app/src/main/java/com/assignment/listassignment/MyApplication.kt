package com.assignment.listassignment

import android.app.Application
import android.content.Context



/**
 * Created by Aparna S
 * The class will start once the application will start and will set the Splunk Key for handling
 *
 */

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }


    override fun onTerminate() {
        super.onTerminate()
    }

}
