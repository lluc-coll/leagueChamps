package com.example.leaguechamps.dataclass.allChamp


data class AllChamps (

	val type : String,
	val format : String,
	val version : String,
	val data : MutableList<AChamps>
)