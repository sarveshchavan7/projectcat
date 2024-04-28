package com.catcompany.projectcat.datamanager.network

import com.catcompany.projectcat.model.Breed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/breeds")
    suspend fun getCatBreedS(
        @Query("page") pageNumber: Int,
        @Query("limit") limit: Int
    ): Response<List<Breed>>
}