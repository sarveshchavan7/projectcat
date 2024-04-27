package com.catcompany.base.crashlytics

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface CrashlyticsServiceProvider {
    fun crashlyticsService(): CrashlyticsService
}