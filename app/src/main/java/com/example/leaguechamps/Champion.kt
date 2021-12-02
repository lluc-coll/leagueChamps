package com.example.leaguechamps

data class Champion (val id: String, val name: String, val title: String, val iconUrl: String, val lore: String, val tags: MutableList<String>, val attack: Int, val defence: Int, val magic: Int, val difficulty: Int, var fav: Boolean, var spells: Spells?, var skins: MutableList<Skin>?)