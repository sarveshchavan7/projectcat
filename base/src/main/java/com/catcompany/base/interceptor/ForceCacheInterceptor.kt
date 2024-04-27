package com.catcompany.base.interceptor

import android.app.Application
import com.catcompany.base.exception.NoInternetException
import com.catcompany.base.utils.hasAnnotation
import com.catcompany.base.utils.isNetworkConnected
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class ForceCacheInterceptor(val application: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val isGetReq = chain.request().method().equals(CacheInterceptor.GET)
        val isApiCache = chain.request().hasAnnotation(ApiCache::class.java)
        val isNetworkAvailable = isNetworkConnected(application)
        val builder = chain.request().newBuilder()
        if (!isNetworkAvailable && isApiCache && isGetReq) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
        } else if (isApiCache && isGetReq) {
            val cacheControl = CacheControl.Builder().noCache().build()
            builder.cacheControl(cacheControl)
        } else if (!isNetworkAvailable) {
            throw NoInternetException()
        }
        return chain.proceed(builder.build());
    }
}