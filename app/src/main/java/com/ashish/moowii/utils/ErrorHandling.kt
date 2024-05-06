package com.ashish.moowii.utils

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber

fun Throwable.getMovieErrorMessage(): String {
    Timber.e(this)
    return if (this is HttpException) {
        val responseBody: ResponseBody? = this.response()?.errorBody()
        try {
            val jsonObject = responseBody?.string()?.let { JSONObject(it) }
            jsonObject?.getString("status_message") ?: this.toString()
        } catch (e: Exception) {
            Timber.e(e.message)
            e.toString()
        }
    } else {
        this.toString()
    }
}
