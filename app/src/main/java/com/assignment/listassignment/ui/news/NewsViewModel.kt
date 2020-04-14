package com.assignment.listassignment.ui.news

import android.app.Activity
import android.media.MediaSession2
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.listassignment.model.newlist.ContributorModel
import com.assignment.listassignment.model.newlist.NewsListResponse
import com.assignment.listassignment.repository.ApiUtilities
import com.assignment.listassignment.repository.ErrorModel
import com.assignment.listassignment.repository.NetworkManager
import com.assignment.listassignment.repository.ServiceListener
import com.assignment.listassignment.utill.AppInstance
import com.assignment.listassignment.utill.SOURCE_DYNAMIC_URL

/**
 * Created by Aparna S.
 */
class NewsViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var apiError = MutableLiveData<String>()
    var apiResponse = MutableLiveData<Any>()

    init {
        isLoading.value = false
    }

    /**
     * Get News List Webservice Call
     */
    fun callNewsListApi(
        activity: Activity,
        search: String
    ) {
        try {
            if (isLoading.value == false) {
                isLoading.value = true
                AppInstance.flagBaseAPI=true
                AppInstance.contributor_url=""
                val manager = NetworkManager()
                manager.createApiRequest(
                    ApiUtilities.getAPIService().getNewsList(search),
                    object : ServiceListener<NewsListResponse> {
                        override fun getServerResponse(
                            response: NewsListResponse,
                            requestcode: Int
                        ) {
                            System.out.println("Favorite Doctor List Response ==> " + response)
                            apiResponse.value = response
                            //apiError.value = response.getMessageText()
                            isLoading.value = false
                        }

                        override fun getError(error: ErrorModel, requestcode: Int) {
                            apiError.value = error.error_message
                            isLoading.value = false
                        }
                    })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            apiError.value = ""
            isLoading.value = false
        }
    }



    /**
     * Get COntributor List Webservice Call
     */
    fun callContributorListApi(
        activity: Activity,
        search: String
    ) {
        try {
            if (isLoading.value == false) {
                isLoading.value = true
                AppInstance.flagBaseAPI=false
                AppInstance.contributor_url=search
                val manager = NetworkManager()
                manager.createApiRequest(
                    ApiUtilities.getAPIService().getContributorList(),
                    object : ServiceListener<ArrayList<ContributorModel>> {
                        override fun getServerResponse(
                            response: ArrayList<ContributorModel>,
                            requestcode: Int
                        ) {
                            //apiResponse.value = response
                            //apiError.value = response.getMessageText()
                           // isLoading.value = false
                        }

                        override fun getError(error: ErrorModel, requestcode: Int) {
                            apiError.value = error.error_message
                            isLoading.value = false
                        }
                    })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            apiError.value = ""
            isLoading.value = false
        }
    }




}