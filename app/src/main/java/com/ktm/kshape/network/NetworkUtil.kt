package com.ktm.kshape.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkUtil {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.colourlovers.com/")
        .build()

    val colorService: ColorService = retrofit.create(ColorService::class.java)
}