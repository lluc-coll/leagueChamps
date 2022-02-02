package com.example.leaguechamps.data.api

import com.example.leaguechamps.data.dataclass.ChampionListData
import com.example.leaguechamps.data.dataclass.ItemList
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("/cdn/{version}/data/{language}/champion.json")
    suspend fun getChampions(@Path("version") version: String, @Path("language") language: String): Response<ChampionListData>

    @GET("/cdn/{version}/data/{language}/champion/{championName}.json")
    suspend fun getChampion(@Path("version") version: String, @Path("language") language: String, @Path("championName") championName: String): Response<ChampionListData>

    @GET("/cdn/{version}/data/{language}/item.json")
    suspend fun getItems(@Path("version") version: String, @Path("language") language: String): Response<ItemList>

    @GET("/cdn/languages.json")
    suspend fun getLanguages(): Response<List<String>>

    @GET("/api/versions.json")

    suspend fun getVersions(): Response<List<String>>



    companion object {
        val BASE_URL = "https://ddragon.leagueoflegends.com/"

        fun create(): APIService {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(APIService::class.java)
        }
    }

}