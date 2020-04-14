package com.assignment.listassignment.repository



import com.assignment.listassignment.model.newlist.ContributorModel
import com.assignment.listassignment.model.newlist.NewsListResponse
import com.assignment.listassignment.utill.AppInstance
import com.assignment.listassignment.utill.AppInstance.contributor_url
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import okhttp3.ResponseBody
import retrofit2.Call


interface APIService {

    /**
     * Created by Aparna S.
     * @Base APIService interface :  This interface contain the all the methods
    of apis (Communicate to  servers with predefined parameters ).
     **/

    @GET("repositories")
    fun getNewsList(@Query("q") order: String): Observable<NewsListResponse>


    @GET(contributor_url)
    fun getContributorList(): Observable<ArrayList<ContributorModel>>


}