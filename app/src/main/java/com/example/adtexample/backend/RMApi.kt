package com.example.adtexample.backend

import com.example.adtexample.model.responses.CharacterResponseObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RMApi {

    @GET("/api/character/")
    suspend fun getCharacters(@Query("page") page: Int) : CharacterResponseObject

    companion object {
        fun getInstance() : RMApi {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.rickandmortyapi.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(RMApi::class.java)
        }
    }
}