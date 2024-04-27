package com.catcompany.projectcat.crashlytics

import android.util.Log
import com.catcompany.base.crashlytics.CrashlyticsService
import com.catcompany.base.utils.getErrorDisplayMessage
import com.catcompany.projectcat.BuildConfig

import com.google.firebase.crashlytics.FirebaseCrashlytics
import java.util.concurrent.CancellationException

class FireBaseCrashlyticsService : CrashlyticsService {
    override fun log(msg: String, tag: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        } else {
            FirebaseCrashlytics.getInstance().log(msg)
        }
    }

    override fun log(e: Throwable, tag: String?) {
        if (e is CancellationException) return
        if (BuildConfig.DEBUG) {
            log(e.getErrorDisplayMessage(), tag)
        } else {
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }
}