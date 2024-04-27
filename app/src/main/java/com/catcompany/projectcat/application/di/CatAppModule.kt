package com.catcompany.projectcat.application.di

import android.app.Application
import com.catcompany.analytics.AnalyticsHelper
import com.catcompany.base.crashlytics.CrashlyticsService
import com.catcompany.base.interceptor.CacheInterceptor
import com.catcompany.base.interceptor.ForceCacheInterceptor
import com.catcompany.base.interceptor.HeaderInterceptor
import com.catcompany.projectcat.BuildConfig
import com.catcompany.projectcat.analytics.FireBaseAnalyticsHelper
import com.catcompany.projectcat.application.CatApp
import com.catcompany.projectcat.datamanager.DataManager
import com.catcompany.projectcat.datamanager.network.ApiHelper
import com.catcompany.projectcat.datamanager.network.ApiHelperImpl
import com.catcompany.projectcat.datamanager.network.ApiService
import com.catcompany.projectcat.crashlytics.FireBaseCrashlyticsService
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CatAppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(application: Application): OkHttpClient {
        // 50MB of cache, source : retrofit documentation.
        val cache = Cache(File(application.cacheDir, "http-cache"), 50L * 1024L * 1024L)
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(ChuckerInterceptor.Builder(application).build())
            .addInterceptor(HeaderInterceptor(provideHeader()))
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor(application))
            .build()
    }

    private fun provideHeader(): Map<String, String> {
        return mapOf("x-api-key" to BuildConfig.CAT_API_KEY)
    }

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL).build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDataManager(
        analyticsHelper: AnalyticsHelper,
        apiHelper: ApiHelper
    ): DataManager {
        return DataManager(analyticsHelper, apiHelper)
    }

    @Singleton
    @Provides
    fun provideApiHelper(apiService: ApiService): ApiHelper {
        return ApiHelperImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideFirebaseAnalytics(application: Application): AnalyticsHelper {
        return FireBaseAnalyticsHelper((application as CatApp).firebaseAnalytics)
    }

    @Singleton
    @Provides
    fun provideCrashlyticsService(): CrashlyticsService {
        return FireBaseCrashlyticsService()
    }
}