package com.assignment.listassignment.model.newlist

import com.google.gson.annotations.SerializedName


class NewsListResponse {


/*

    @SerializedName("total_count")
    @Expose
    var totalCount: Int? = null
    @SerializedName("incomplete_results")
    @Expose
    var incompleteResults: Boolean? = null
    @SerializedName("items")
    @Expose
    var items: ArrayList<ItemList>? = null
    
    
*/
@SerializedName("total_count")
var totalCount: Int? = null
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean? = null
    @SerializedName("items")
    var items: ArrayList<ItemList>? = null

}
