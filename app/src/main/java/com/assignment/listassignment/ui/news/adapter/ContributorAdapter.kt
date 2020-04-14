package com.assignment.listassignment.ui.news.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.assignment.listassignment.R
import com.assignment.listassignment.model.newlist.ContributorModel
import com.assignment.listassignment.model.newlist.ItemList
import com.assignment.listassignment.utill.showImage

class ContributorAdapter(
    activity: Context?,
    private var allList: ArrayList<ContributorModel>?
) : RecyclerView.Adapter<ContributorAdapter.ViewHolder>() {

    var context: Context? = activity
    var colorCode = ""
    var selectedPosition = -1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_list,
                p0,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return allList!!.size
    }


    fun getCount(): Int {
        return allList!!.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val data = allList?.get(position)!!
            if (!data.avatarUrl.isNullOrEmpty()) {
                showImage(holder.ivThumbnail, data.avatarUrl!!, R.drawable.border)
            } else {
                holder.ivThumbnail.setImageResource(R.drawable.border)
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivThumbnail: ImageView = view.findViewById(R.id.ivThumbnail)

        var mIsInTheMiddle=false

        fun getIsInTheMiddle(): Boolean {
            return mIsInTheMiddle
        }
    }



}
