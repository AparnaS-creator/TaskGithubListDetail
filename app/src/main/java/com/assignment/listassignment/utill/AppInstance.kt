package com.assignment.listassignment.utill

import com.assignment.listassignment.model.newlist.NewsListResponse


/**
 * Created by Aparna S.
 */
object AppInstance {

    private var appInstance: AppInstance? = null
    var newsDataObj: NewsListResponse? = null
    var flagBaseAPI:Boolean=false
    var contributor_url:String=""

    //find Doc
    var modeType:String = ""


    /**
     * To initialize the appInstance Object
     *
     * @return singleton instance
     */

    fun getAppInstance(): AppInstance {
        if (appInstance == null) {
            appInstance = AppInstance()

            /**
             * The object will manage the UserData information
             */
            newsDataObj = NewsListResponse()

        }

        return appInstance as AppInstance
    }

    private operator fun invoke(): AppInstance? {
        return null
    }

}