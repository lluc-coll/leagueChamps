package com.example.leaguechamps.dataclass.eachChamp


data class EChamp (

	val id : String,
	val key : Int,
	val name : String,
	val title : String,
	val image : Image,
	val skins : List<Skins>,
	val lore : String,
	val blurb : String,
	val allytips : List<String>,
	val enemytips : List<String>,
	val tags : List<String>,
	val partype : String,
	val info : Info,
	val stats : Stats,
	val spells : List<Spells>,
	val passive : Passive,
	val recommended : List<String>
)