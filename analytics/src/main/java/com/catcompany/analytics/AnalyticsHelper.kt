package com.catcompany.analytics

interface AnalyticsHelper {
    fun trackClickEvent(eventName: String, params: Map<String, Any?>)
    fun trackScreenVisitedEvent(eventName: String, params: Map<String, Any?>)
}