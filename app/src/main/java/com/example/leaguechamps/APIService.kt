package com.example.leaguechamps

import com.example.leaguechamps.dataclass.ChampionListData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("/cdn/11.23.1/data/en_US/champion.json")
    fun getChampions(): Call<ChampionListData>?

    @GET("/cdn/11.23.1/data/en_US/champion/{championName}.json")
    fun getChampion(@Path("championName") championName: String): Call<ChampionListData>?

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