package com.assignment.listassignment.utill

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Aparna S
 *  This class provides re usable functions
 */
object Utility {

    /**
     * to provide network availability
     */
    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }



}