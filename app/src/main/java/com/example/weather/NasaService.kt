package com.example.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {
    @GET("data/2.5/weather?")
    fun getCurrentNasaData(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Call<NasaResponse>
}
