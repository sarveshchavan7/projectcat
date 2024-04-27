package com.catcompany.base.crashlytics

interface CrashlyticsService {
    fun log(msg: String, tag: String? = TAG)
    fun log(e: Throwable, tag: String? = TAG)

    companion object {
        const val TAG = "CrashlyticsService"
    }
}