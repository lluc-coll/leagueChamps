package com.example.leaguechamps.data.dataclass


data class ChampionListData(
    var keys: Map<String, String>?,
    var data: Map<String, ChampionData>?,
    var version: String?,
    var type: String?,
    var format: String?)