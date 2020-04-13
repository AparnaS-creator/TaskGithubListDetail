package com.assignment.listassignment.ui.news.adapter

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.assignment.listassignment.R
import com.assignment.listassignment.model.newlist.ItemList
import com.assignment.listassignment.utill.showImage




/**
 * Created by Aparna S
 */
class NewsListAdapter(
    activity: Context?,
    private var allList: ArrayList<ItemList>?
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

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
            if (!data.owner?.avatarUrl.isNullOrEmpty()) {
                showImage(holder.ivThumbnail, data.owner?.avatarUrl!!, R.drawable.border)
            } else {
                holder.ivThumbnail.setImageResource(R.drawable.border)
            }
            holder.tvHeader.text = data.name
            holder.tvInfo1.text = data.fullName
            holder.tvInfo2.text= context!!.getString(R.string.watcher_txt)+" "+data.watchersCount

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivThumbnail: ImageView = view.findViewById(R.id.ivThumbnail)
        val tvHeader: TextView = view.findViewById(R.id.tvHeader)
        val tvInfo1: TextView = view.findViewById(R.id.tvInfo1)
        val tvInfo2: TextView = view.findViewById(R.id.tvInfo2)

        var mIsInTheMiddle=false

        fun getIsInTheMiddle(): Boolean {
            return mIsInTheMiddle
        }
    }

    interface ItemClickListener {
        fun onItemClick(data: ItemList, position: Int)

    }


    fun updateUI(position: Int, colorCode: String) {
        selectedPosition = position
        notifyItemChanged(position)
    }


}

