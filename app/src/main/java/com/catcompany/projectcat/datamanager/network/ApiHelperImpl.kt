package com.catcompany.projectcat.datamanager.network

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getCatBreeds() = apiService.getCatBreedS()
}