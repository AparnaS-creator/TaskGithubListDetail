@file:Suppress("DEPRECATION")

package com.assignment.listassignment.utill

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.net.Uri
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.assignment.listassignment.R
import com.squareup.picasso.Picasso
import org.json.JSONObject

/**
 * Created by Aparna S.
 * it contain all the common methods that are uses as per requirement
 */


/**
 * check network is available or not
 */
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

/**
 * show toast
 */
fun showToast(mContext: Context, message: String, duration: Int) {
    try {
        val toast = Toast.makeText(mContext, message, duration)
        val view = toast.view
        val text = view.findViewById<TextView>(android.R.id.message)
        view.background.setColorFilter(
            Color.parseColor("#19769f"),
            PorterDuff.Mode.SRC_IN
        );
        text.setTextColor(Color.WHITE)
        text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_info_icon, 0, 0, 0)
        text.compoundDrawablePadding = 10
        toast.setGravity(Gravity.BOTTOM, 40, 40)
        toast.show()
    } catch (e: Exception) {
        e.printStackTrace()
        try {
            val toast = Toast.makeText(mContext, message, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.BOTTOM, 40, 40)
            toast.show()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}


fun parseError(result: String?): String {
    var errorDescription = ""
    try {
        if (result != null) {
            val jsonObjectResult = JSONObject(result)
            errorDescription = jsonObjectResult.getString("messageText")
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
        errorDescription = result.toString()
    }

    return errorDescription

}

fun isImageFile(imageType: String): Boolean {
    return imageType.equals("image/jpeg", ignoreCase = true) || imageType.equals(
        "image/jpg",
        ignoreCase = true
    ) || imageType.equals("image/png", ignoreCase = true) || imageType.equals(
        "image/gif",
        ignoreCase = true
    )
}


private fun openBrowser(link: String, context: Context) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(intent)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}


fun hideSoftKeyboard(context: Context, et: EditText) {
    try {
        val imm = context.getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(et.windowToken, 0)
    } catch (e: Exception) {
    }
}

fun hideVirtualKeyboard(mContext: Context) {
    try {
        val binder = (mContext as Activity).window.currentFocus!!
            .windowToken
        if (binder != null) {
            val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binder, 0)
        }
    } catch (e: Exception) {
        //no focus found
    }
}


/**
 *  display Image
 */
fun showImage(imageView: ImageView, pic: String, placeholder: Int) {
    try {
        Picasso.get()
            .load(pic)
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    } catch (e: Exception) {
        e.printStackTrace()
        imageView.setImageResource(placeholder)
    }
}


fun replaceString(str1:String):String{
    var str = str1
    val oldValue = "https://api.github.com/search/"
    val newValue = ""

    return( str.replace(oldValue, newValue))
}










