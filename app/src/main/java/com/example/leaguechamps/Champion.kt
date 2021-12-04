package com.example.leaguechamps

data class Champion (val id: Any?, val name: String?, val title: String?, val iconUrl: String?, val lore: String?, val tags: List<String>?, val attack: Int?, val defence: Int?, val magic: Int?, val difficulty: Int?, var fav: Boolean)