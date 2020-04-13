package com.assignment.listassignment.widget.loader

import android.content.Context

/**
 * Created by Swapnil Nandapure.
 */
class UtilLoader {
    lateinit var progressDialogObj: TransparentProgressDialog
    lateinit var context: Context

    constructor(context: Context) {
        this.context = context
    }


    fun startLoader(context: Context) {
        try {
            progressDialogObj = TransparentProgressDialog(context)
            progressDialogObj.show()
        } catch (e: Exception) {
        }
    }

    fun stopLoader() {
        try {
            if (progressDialogObj != null)
                progressDialogObj.dismiss()
        } catch (e: Exception) {
        }
    }


}