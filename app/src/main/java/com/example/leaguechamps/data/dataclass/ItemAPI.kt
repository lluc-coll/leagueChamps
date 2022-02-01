package com.example.leaguechamps.data.dataclass



data class ItemAPI(
    var gold: ItemGold?,
    var plaintext: String?,
    var hideFromAll: Boolean?,
    var inStore: Boolean?,
    var into: List<String>?,
    var id: Int?,
    var stats: ItemInventoryDataStats?,
    var colloq: String?,
    var maps: Map<String, Boolean>?,
    var specialRecipe: Int?,
    var image: Image?,
    var description: String?,
    var tags: List<String>?,
    var effect: Map<String, String>?,
    var requiredChampion: String?,
    var from: List<String>?,
    var group: String?,
    var consumeOnFull: Boolean?,
    var name: String?,
    var consumed: Boolean?,
    var sanitizedDescription: String?,
    var depth: Int?,
    var stacks: Int?
)