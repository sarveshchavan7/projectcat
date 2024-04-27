package com.catcompany.projectcat.application

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CatApp : Application() {

    lateinit var firebaseAnalytics: FirebaseAnalytics
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        firebaseAnalytics = Firebase.analytics
    }
}