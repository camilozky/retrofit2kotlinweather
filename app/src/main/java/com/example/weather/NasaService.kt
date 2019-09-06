package com.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {
    @GET("apod?")
    fun getCurrentNasaData(
        @Query("api_key") app_id: String
    ): Call<NasaResponse>
}
