package com.assignment.listassignment.repository

/**
 * Created by Aparna S.
 * @ResponseCodes class contain all the static values of responses
 */
class ResponseCodes {

    companion object {


        /** System Level Response Codes Starts Here **/

        /** The Constant Success.  */
        val Success = 200

        // Error Codes
        val REQUEST_CANCEL = -1
        val RESPONSE_JSON_NOT_VALID = 1
        val MODEL_TYPE_CAST_EXCEPTION = 2
        val INTERNET_NOT_AVAILABLE = 3
        val WRONG_URL = 4
        val WRONG_METHOD_NAME = 5
        val URL_CONNECTION_ERROR = 6
        val UNKNOWN_ERROR = 10

        val NOT_ALLOWED = 403


        fun logErrorMessage(code: Int): String {
            var errorMessage = ""

            when (code) {

                ResponseCodes.Companion.REQUEST_CANCEL -> errorMessage = "Request Canceled"

                ResponseCodes.Companion.INTERNET_NOT_AVAILABLE -> errorMessage =
                    "Internet connection is not available. Please check it and try again"

                ResponseCodes.Companion.WRONG_URL -> errorMessage =
                    "You are trying to hit wrong url, Please check it and try again"

                ResponseCodes.Companion.WRONG_METHOD_NAME -> errorMessage =
                    "You are passing wrong method name. i.e POST, GET, DELETE etc"

                ResponseCodes.Companion.URL_CONNECTION_ERROR -> errorMessage =
                    "Connection is not established, Please try again"


                ResponseCodes.Companion.RESPONSE_JSON_NOT_VALID ->

                    errorMessage = "Json you are getting is not valid"

                ResponseCodes.Companion.MODEL_TYPE_CAST_EXCEPTION -> errorMessage =
                    "Server is not working. Please try after some time."

                ResponseCodes.Companion.NOT_ALLOWED -> errorMessage =
                    "Server is not working. Please try after some time."

                else -> errorMessage = "Unknown error"
            }
            return errorMessage
        }
    }
}