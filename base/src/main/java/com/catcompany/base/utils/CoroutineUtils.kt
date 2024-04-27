package com.catcompany.base.utils

import android.content.Context
import com.catcompany.base.crashlytics.CrashlyticsServiceProvider
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler


fun coroutineExceptionHandler(context: Context) = CoroutineExceptionHandler { _, throwable ->
    if (throwable is CancellationException) return@CoroutineExceptionHandler
    val hiltEntryPoint = EntryPointAccessors
        .fromApplication(context, CrashlyticsServiceProvider::class.java)
    val crashlyticsService = hiltEntryPoint.crashlyticsService()
    crashlyticsService.log(throwable)
    throw throwable
}
