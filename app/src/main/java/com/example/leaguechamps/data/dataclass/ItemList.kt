package com.example.leaguechamps.data.dataclass




data class ItemList(
    var data: Map<String, ItemAPI>?,
    var version: String?,
    var tree: List<ItemTree>?,
    var groups: List<ItemGroup>?,
    var type: String?)