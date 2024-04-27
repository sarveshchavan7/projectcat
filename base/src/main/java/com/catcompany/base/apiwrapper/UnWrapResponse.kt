package com.catcompany.base.apiwrapper

import retrofit2.Response
import java.io.IOException

const val TAG = "UNWRAPRESPONSE"

fun <T> Response<T>.unWrap(): T {
    if (!this.isSuccessful) throw IOException(this.message())
    return body() ?: throw IOException(message())
}