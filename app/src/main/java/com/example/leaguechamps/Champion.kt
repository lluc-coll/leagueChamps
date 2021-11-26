package com.example.leaguechamps

data class Champion (val id: String, val name: String, val title: String, val iconUrl: Int, val lore: String, val tags: MutableList<String>, val attack: Int, val defence: Int, val magic: Int, val difficulty: Int)