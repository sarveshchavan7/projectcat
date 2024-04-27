package com.catcompany.projectcat.datamanager.network

import com.catcompany.base.interceptor.ApiCache
import com.catcompany.projectcat.model.Breed
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @ApiCache
    @GET("v1/breeds")
    suspend fun getCatBreedS(): Response<List<Breed>>
}