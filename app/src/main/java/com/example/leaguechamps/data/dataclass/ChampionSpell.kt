package com.example.leaguechamps.data.dataclass



data class ChampionSpell(
        var cooldownBurn: String?,
        var resource: String?,
        var leveltip: LevelTip?,
        var vars: List<ChampionSpellVar>?,
        var costType: String?,
        var image: Image?,
        var sanitizedDescription: String?,
        var sanitizedTooltip: String?,
        var effect: List<List<Double>>?,
        var tooltip: String?,
        var maxrank: Int?,
        var costBurn: String?,
        var rangeBurn: String?,
        var range: List<Int>?,
        var cooldown: List<Double>?,
        var cost: List<Int>?,
        var key: String?,
        var description: String?,
        var effectBurn: List<String>?,
        var altimages: List<Image>?,
        var name: String?)