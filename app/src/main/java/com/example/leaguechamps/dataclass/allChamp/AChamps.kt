package com.example.leaguechamps.dataclass.allChamp

import com.example.leaguechamps.dataclass.eachChamp.Image
import com.example.leaguechamps.dataclass.eachChamp.Info
import com.example.leaguechamps.dataclass.eachChamp.Stats



data class AChamps (

	val version : String,
	val id : String,
	val key : Int,
	val name : String,
	val title : String,
	val blurb : String,
	val info : Info,
	val image : Image,
	val tags : List<String>,
	val partype : String,
	val stats : Stats
)