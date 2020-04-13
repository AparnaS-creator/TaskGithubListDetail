package com.assignment.listassignment.ui.news

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.listassignment.model.newlist.NewsListResponse
import com.assignment.listassignment.repository.ApiUtilities
import com.assignment.listassignment.repository.ErrorModel
import com.assignment.listassignment.repository.NetworkManager
import com.assignment.listassignment.repository.ServiceListener

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
     * Get Favorite Doctor List Webservice Call
     */
    fun callNewsListApi(
        activity: Activity,
        search: String
    ) {
        try {
            if (isLoading.value == false) {
                isLoading.value = true
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
     *  navigation to News Details
     */
    fun goToDoctorsDetails(activity: Activity) {
       /* val intent = Intent(activity, NewsDetailsActivity::class.java)
        intent.putExtra(SOURCE, SOURCE_FAVORITE_DOCTOR)
        activity.startActivity(intent)
        activity.overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )  // for open*/
    }



}