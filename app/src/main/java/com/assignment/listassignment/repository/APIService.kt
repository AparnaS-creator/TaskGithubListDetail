package com.assignment.listassignment.repository



import com.assignment.listassignment.model.newlist.NewsListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    /**
     * Created by Aparna S.
     * @Base APIService interface :  This interface contain the all the methods
    of apis (Communicate to  servers with predefined parameters ).
     **/

    @GET(WebConstants.GIT_LIST)
    fun getNewsList(@Query("q") order: String): Observable<NewsListResponse>


}