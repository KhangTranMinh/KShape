package com.ktm.kshape.network

import retrofit2.http.GET

interface ColorService {

    @GET("api/colors/random?format=json")
    suspend fun getRandomColor(): RandomColorResponse?

    @GET("api/patterns/random?format=json")
    suspend fun getRandomImage(): RandomImageResponse?
}