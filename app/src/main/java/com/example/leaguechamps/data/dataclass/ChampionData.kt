package com.example.leaguechamps.data.dataclass


class ChampionData(
    var info: ChampionDataInfo?,
    var enemytips: List<String>?,
    var stats: ChampionStats?,
    var name: String?,
    var title: String?,
    var image: Image?,
    var tags: List<String>?,
    var partype: String?,
    var skins: List<ChampionSkin>?,
    var passive: ChampionPassive?,
    var recommended: List<ChampionRecommandedData>?,
    var allytips: List<String>?,
    var key: String?,
    var lore: String?,
    var id: Any?,
    var blurb: String?,
    var spells: List<ChampionSpell>?)