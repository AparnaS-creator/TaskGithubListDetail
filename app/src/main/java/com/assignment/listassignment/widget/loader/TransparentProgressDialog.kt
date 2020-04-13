package com.assignment.listassignment.widget.loader

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import com.assignment.listassignment.R

/**
 * Created by Aparna S.
 */
class TransparentProgressDialog

    (context: Context) : Dialog(context, R.style.TransparentProgressDialog) {

    private val iv: ImageView? = null

    init {
        val wlmp = window?.attributes
        wlmp?.gravity = Gravity.CENTER_HORIZONTAL
        window?.attributes = wlmp
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        setContentView(R.layout.progress_bar_layout)
    }

    override fun show() {
        super.show()

    }


    override fun dismiss() {
        super.dismiss()

    }
}