package com.catcompany.projectcat.analytics

import android.os.Bundle
import com.catcompany.analytics.AnalyticsHelper
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ParametersBuilder

class FireBaseAnalyticsHelper(private val firebaseAnalytics: FirebaseAnalytics) : AnalyticsHelper {

    override fun trackClickEvent(eventName: String, params: Map<String, Any?>) {
        firebaseAnalytics.logEvent(eventName, params.getParams())
    }

    override fun trackScreenVisitedEvent(eventName: String, params: Map<String, Any?>) {
        firebaseAnalytics.logEvent(eventName, params.getParams())
    }
}

fun Map<String, Any?>.getParams(): Bundle {
    val parameterBuilder = ParametersBuilder()
    this.map {
        when (val value = it.value) {
            is String -> parameterBuilder.param(it.key, value)
            is Long -> parameterBuilder.param(it.key, value)
            is Double -> parameterBuilder.param(it.key, value)
            else -> throw IllegalArgumentException("Unsupported parameter type for key: ${it.key}")
        }
    }
    return parameterBuilder.bundle
}
