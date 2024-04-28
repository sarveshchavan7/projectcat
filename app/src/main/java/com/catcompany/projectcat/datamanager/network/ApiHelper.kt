package com.catcompany.projectcat.datamanager.network

import com.catcompany.projectcat.model.Breed
import retrofit2.Response

interface ApiHelper {
    suspend fun getCatBreeds(pageNumber : Int, limit : Int): Response<List<Breed>>
}