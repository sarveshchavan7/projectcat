package com.catcompany.base.interceptor

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val headers: Map<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val headerBuilder = Headers.Builder()
        headers.map {
            headerBuilder.add(it.key, it.value)
        }
        requestBuilder.headers(headerBuilder.build())
        return chain.proceed(requestBuilder.build())
    }
}