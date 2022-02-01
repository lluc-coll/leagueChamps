package com.example.leaguechamps.data.api

import com.example.leaguechamps.data.dataclass.ChampionListData
import com.example.leaguechamps.data.dataclass.ItemList
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("/cdn/{version}/data/{language}/champion.json")
    fun getChampions(@Path("version") version: String, @Path("language") language: String): Call<ChampionListData>?

    @GET("/cdn/{version}/data/{language}/champion/{championName}.json")
    fun getChampion(@Path("version") version: String, @Path("language") language: String, @Path("championName") championName: String): Call<ChampionListData>?

    @GET("/cdn/{version}/data/{language}/item.json")
    fun getItems(@Path("version") version: String, @Path("language") language: String): Call<ItemList>?

    @GET("/cdn/languages.json")
    fun getLanguages(): Call<List<String>>

    @GET("/api/versions.json")
    fun getVersions(): Call<List<String>>



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