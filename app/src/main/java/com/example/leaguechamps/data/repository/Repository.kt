package com.example.leaguechamps.data.repository

import com.example.leaguechamps.data.api.APIService

class Repository {
    private val api = APIService.create()

    suspend fun getChamps(version: String, language: String) = api.getChampions(version, language)
    suspend fun getChamp(version: String, language: String, idChamp: String) = api.getChampion(version, language, idChamp)
    suspend fun getItems(version: String, language: String) = api.getItems(version, language)
    suspend fun getVersions() = api.getVersions()
    suspend fun getLanguages() = api.getLanguages()
}