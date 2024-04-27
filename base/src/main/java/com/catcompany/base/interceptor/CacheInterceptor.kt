package com.catcompany.base.interceptor

import com.catcompany.base.utils.hasAnnotation
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit


class CacheInterceptor : Interceptor {

    companion object {
        const val GET = "GET"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val isGetReq = chain.request().method().equals(GET)
        val isApiCache = chain.request().hasAnnotation(ApiCache::class.java)

        val response: Response = chain.proceed(chain.request())

        if (isGetReq && isApiCache) {
            val cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.DAYS)
                .build()
            return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
        return response
    }
}